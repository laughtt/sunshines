/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.jose.sunshines;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Utility class with methods to help perform the HTTP request and
 * parse the response.
 */


public final class Utils {
    /** Tag for the log messages */
    public static final String LOG_TAG = Utils.class.getSimpleName();

    /**
     * Query the USGS dataset and return an  object to represent a single earthquake.
     */
    public static ArrayList<ListObject> fetchEarthquakeData(String requestUrl) {
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }

        // Extract relevant fields from the JSON response and create an {@link Event} object
        ArrayList<ListObject> contenido = extractFeatureFromJson(jsonResponse);

        // Return the {@link Event}
        return contenido;
    }

    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with creating URL ", e);
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    /**
     * Return an  object by parsing out information
     * about the first earthquake from the input earthquakeJSON string.
     */
    private static ArrayList<ListObject> extractFeatureFromJson(String SunshineJson) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(SunshineJson)) {
            return null;
        }


        final ArrayList<ListObject> contenido = new ArrayList<>() ;


        try {

            JSONObject baseJsonResponse = new JSONObject(SunshineJson);
            JSONArray featureArray = baseJsonResponse.getJSONArray("list");

            // If there are results in the features array

            for ( int i = 0 ; i< featureArray.length(); i++ ) {

                JSONObject currentear = featureArray.getJSONObject(i);
                JSONObject currentas = currentear.getJSONObject("main");
                String presion = currentas.getString("pressure") ;
                String humedad =currentas.getString("humidity") ;

                //Fecha dia  primer parametro//
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                DateFormat sdf = new SimpleDateFormat("EEEE");
                Date startDate;
                startDate = df.parse(currentear.getString("dt_txt"));
                String nuevadate = sdf.format(startDate);
                //Termina la fecha dia primer parametro //


                //comparacion de datos para grados //
                 int kelvin = (int) Math.floor(Double.parseDouble(currentas.getString("temp"))-273.15);

                 String grados = Double.toString(kelvin);


                  //Datos compraacion //


                JSONArray weather = currentear.getJSONArray("weather");
                JSONObject weather1 = weather.getJSONObject(0) ;
                String time = weather1.getString("description");
                String time2 = weather1.getString("main");
                JSONObject viento = currentear.getJSONObject("wind");
                String velocidadviento = viento.getString("speed") ;
                String direccion = viento.getString("deg") ;




                 if ( i % 7 == 0 ){
                    contenido.add(new ListObject( R.drawable.nube ,nuevadate , time  , grados , humedad , presion , velocidadviento
                    )); }
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the earthquake JSON results", e);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return contenido;
    }
}
