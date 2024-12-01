package com.example.proj1_etr65856;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBSchedule extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "Schedule.DB";
    static final int DATABASE_VERSION = 1;

    static final String DATABASE_TABLE = "DocSchedule";
    static final String SCHEDULE_ID = "schedule_id";
    static final String PATIENT_NAME = "patient_name";
    static final String PNUMBER = "p_number";
    static final String TYPE = "typeofschedule";
    static final String DATE = "date";
    static final String TIME = "time";
    static final String LOCATION = "location";

    private static final String CREATE_DB_QUERY = "CREATE TABLE " + DATABASE_TABLE + " ( "
            + SCHEDULE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PATIENT_NAME + " TEXT NOT NULL, "
            + PNUMBER + " TEXT NOT NULL, "
            + TYPE + " TEXT NOT NULL, "
            + DATE + " TEXT NOT NULL, "
            + TIME + " TEXT NOT NULL, "
            + LOCATION + " TEXT NOT NULL);";

    public DBSchedule(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("CREATE_TAG", "Creating table with query: " + CREATE_DB_QUERY);
        db.execSQL(CREATE_DB_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db); // Recreate the table
    }
}