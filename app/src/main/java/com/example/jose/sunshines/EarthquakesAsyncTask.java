package com.example.jose.sunshines;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.ArrayList;

/**
 * Created by jose on 12/29/2016.
 */

public class EarthquakesAsyncTask extends AsyncTaskLoader< ArrayList<ListObject>> {
    public final static String LINK = "http://api.openweathermap.org/data/2.5/forecast/city?id=3936456&APPID=91f18820b2e37a0b868996febc5efa18";

    public EarthquakesAsyncTask(Context context) {
        super(context);
    }

    @Override
    public ArrayList<ListObject> loadInBackground() {
        ArrayList<ListObject> sunshine = Utils.fetchEarthquakeData(LINK);
        return sunshine;

    } }