package com.example.jose.sunshines.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jose on 1/8/2017.
 */

public class ClimaDb extends SQLiteOpenHelper  {

    private static final String DATABASE_NAME = "clima.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    public ClimaDb(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    public static  final String DELETE_TABLES_ALL = " DELETE FROM " + climacontract.ClimaEntry.TABLE_NAME;
    public static final String  CREATE_CLIMA =  "CREATE TABLE " + climacontract.ClimaEntry.TABLE_NAME + " ("
            + climacontract.ClimaEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + climacontract.ClimaEntry.COLUMN_DIA+ " TEXT NOT NULL, "
            + climacontract.ClimaEntry.COLUMN_TIME + " TEXT, "
            + climacontract.ClimaEntry.COLUMN_TEMPERATURA + " INTEGER NOT NULL, "
            + climacontract.ClimaEntry.COLUMN_HUMEDAD + " TEXT, "
            + climacontract.ClimaEntry.COLUMN_PRESION + " TEXT, "
            + climacontract.ClimaEntry.COLUMN_VIENTO+ " INTEGER NOT NULL DEFAULT 0);";


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
