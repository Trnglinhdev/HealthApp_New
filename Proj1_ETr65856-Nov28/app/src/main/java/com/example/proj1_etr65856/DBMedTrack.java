package com.example.proj1_etr65856;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBMedTrack extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "MedTrack.DB";
    static final int DATABASE_VERSION = 1;

    static final String DATABASE_TABLE = "MedTrack";
    static final String MEDID = "medID";
    static final String PNAME = "p_name";
    static final String PNUMBER = "p_number";
    static final String MED_NAME = "medication";
    static final String QUANTITY = "quantity";
    static final String DATE = "date";
    static final String DURATION = "duration";

    private static final String CREATE_DB_QUERY = "CREATE TABLE " + DATABASE_TABLE + " ( "
            + MEDID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PNAME + " TEXT NOT NULL, "
            + PNUMBER + " TEXT NOT NULL, "
            + MED_NAME + " TEXT NOT NULL, "
            + DATE + " TEXT NOT NULL, "
            + QUANTITY + " TEXT NOT NULL, "
            + DURATION + " TEXT NOT NULL);";

    public DBMedTrack(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("MedTrackCREATE_TAG", "Creating table with query: " + CREATE_DB_QUERY);
        db.execSQL(CREATE_DB_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db); // Recreate the table
    }
}