package com.example.proj1_etr65856;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.SQLDataException;

public class ConsulDBManager {
    private DBColsulHistory dbConsul;
    private final Context context;
    private SQLiteDatabase database;

    public ConsulDBManager(Context ctx){
        context = ctx;
    }

    public ConsulDBManager open() throws SQLDataException {
        dbConsul = new DBColsulHistory(context);
        database = dbConsul.getWritableDatabase();
        return this;
    }

    public void close(){
        dbConsul.close();
    }

    public void insert (String pname, String phoneNumber, String date, String diagnosis, String treatment){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBColsulHistory.PNAME, pname);
        contentValues.put(DBColsulHistory.PNUMBER, phoneNumber);
        contentValues.put(DBColsulHistory.DATE, date);
        contentValues.put(DBColsulHistory.DIAGNOSIS, diagnosis);
        contentValues.put(DBColsulHistory.TREATMENT, treatment);
        database.insert(DBColsulHistory.DATABASE_TABLE, null, contentValues);
    }

    public Cursor fetch(){
        String [] columns = new String [] { DBColsulHistory.COLSULID, DBColsulHistory.PNAME, DBColsulHistory.PNUMBER ,DBColsulHistory.DATE, DBColsulHistory.DIAGNOSIS, DBColsulHistory.TREATMENT};
        Cursor cursor = database.query(DBColsulHistory.DATABASE_TABLE, columns, null, null, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor fetch_colsul(String p_name, String p_pnumber){
        Log.d("DBQuery", "Fetching consultation history for: " + p_name + ", " + p_pnumber);
        return database.query(
                DBColsulHistory.DATABASE_TABLE,
                null,
                DBColsulHistory.PNAME + "=? AND " + DBColsulHistory.PNUMBER + "=?",
                new String[]{p_name, p_pnumber},
                null,
                null,
                null
        );
    }

    public void delete (long id ){
        database.delete(DBColsulHistory.DATABASE_TABLE, DBColsulHistory.COLSULID+ "=" + id, null);
    }

}
