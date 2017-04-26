package com.example.jose.sunshines.Data;

import android.provider.BaseColumns;

/**
 * Created by jose on 1/8/2017.
 */

public class climacontract {
    private climacontract() {}

    /* Inner class that defines the table contents */
    public static class ClimaEntry implements BaseColumns {
        public static final String TABLE_NAME = "Clima";
        public final static String _ID = BaseColumns._ID;
        public static final String COLUMN_DIA = "DIA";
        public static final String COLUMN_TIME = "TIME";
        public static final String COLUMN_TEMPERATURA = "TEMPERATURA";
        public static final String COLUMN_HUMEDAD = "HUMEDAD";
        public static final String COLUMN_PRESION = "PRESION";
        public static final String COLUMN_VIENTO= "VIENTO";

    }
}
