package com.example.proj1_etr65856;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.SQLDataException;

public class MedDBManager {
    private DBMedTrack dbMedTrack;
    private final Context context;
    private SQLiteDatabase database;

    public MedDBManager(Context ctx){
        context = ctx;
    }

    public MedDBManager open() throws SQLDataException {
        dbMedTrack = new DBMedTrack(context);
        database = dbMedTrack.getWritableDatabase();
        return this;
    }

    public void close(){
        dbMedTrack.close();
    }

    public void insert (String pname, String phoneNumber, String med, String quantity, String date, String duration){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBMedTrack.PNAME, pname);
        contentValues.put(DBMedTrack.PNUMBER, phoneNumber);
        contentValues.put(DBMedTrack.MED_NAME, med);
        contentValues.put(DBMedTrack.QUANTITY, quantity);
        contentValues.put(DBMedTrack.DATE, date);
        contentValues.put(DBMedTrack.DURATION, duration);
        database.insert(DBMedTrack.DATABASE_TABLE, null, contentValues);
    }

    public Cursor fetch(){
        String [] columns = new String [] { DBMedTrack.MEDID, DBMedTrack.PNAME, DBMedTrack.PNUMBER ,DBMedTrack.MED_NAME, DBMedTrack.QUANTITY, DBMedTrack.DATE, DBMedTrack.DURATION};
        Cursor cursor = database.query(DBMedTrack.DATABASE_TABLE, columns, null, null, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor fetch_med(String p_name, String p_pnumber){
        Log.d("DBQuery", "Fetching medications for: " + p_name + ", " + p_pnumber);
        return database.query(
                DBMedTrack.DATABASE_TABLE,
                null,
                DBMedTrack.PNAME + "=? AND " + DBMedTrack.PNUMBER + "=?",
                new String[]{p_name, p_pnumber},
                null,
                null,
                null
        );
    }

    public void delete (long id ){
        database.delete(DBMedTrack.DATABASE_TABLE, DBMedTrack.MEDID+ "=" + id, null);
    }

}
