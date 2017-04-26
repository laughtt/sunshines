package com.example.jose.sunshines;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import java.io.Serializable;

/**
 * Created by jose on 12/25/2016.
 */

public class ListObject implements Serializable {

    int imagen  ;
    private String day , time , gradosmax  , humedad , presion  , viento ,gradosmin ;



    public ListObject ( int imagen, String day , String time , String gradosmax  , String humedad , String presion , String viento ) {

        this.imagen =  imagen ;
        this.day = day;
        this.time = time ;
        this.gradosmax = gradosmax ;
        this.humedad = humedad ;
        this.presion = presion ;
        this.viento = viento ;
        this.gradosmin = gradosmin;


    }

public String getDay (){
    return day ;

}
public String getTime(){

    return  time;

}
    public int getImagen(){
        return  imagen ;

    }

public String getGradosmax (){
    return  gradosmax ;
}
public  String getHumedad() {

    return  humedad ;
}
public String getPresion () {
    return  presion;
}
public String getViento () {

    return viento ;
}
    public String getGradosmin(){

        return gradosmin;
    }


}
