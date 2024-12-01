package com.example.proj1_etr65856;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLDataException;

public class DatabaseManager {
    private DBPatient dbHelper;
    private final Context context;
    private SQLiteDatabase database;

    public DatabaseManager(Context ctx){
        context = ctx;
    }

    public DatabaseManager open() throws SQLDataException{
        dbHelper = new DBPatient(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dbHelper.close();
    }

    public void insert (String firstName, String lastName, String dateOfBirth, String gender, String phoneNumber, String email, String emergencyContact){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBPatient.F_NAME, firstName);
        contentValues.put(DBPatient.L_NAME, lastName);
        contentValues.put(DBPatient.DOB, dateOfBirth);
        contentValues.put(DBPatient.GENDER, gender);
        contentValues.put(DBPatient.PNUMBER, phoneNumber);
        contentValues.put(DBPatient.EMAIL, email);
        contentValues.put(DBPatient.E_CONTACT, emergencyContact);
        database.insert(DBPatient.DATABASE_TABLE, null, contentValues);
    }

    public Cursor fetch(){
        String [] columns = new String [] { DBPatient.PNUMBER, DBPatient.F_NAME, DBPatient.L_NAME, DBPatient.DOB, DBPatient.GENDER, DBPatient.EMAIL, DBPatient.E_CONTACT};
        Cursor cursor = database.query(DBPatient.DATABASE_TABLE, columns, null, null, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor fetch_fname(){
        String [] columns = new String [] {DBPatient.F_NAME};
        Cursor cursor = database.query(DBPatient.DATABASE_TABLE, columns, null, null, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor fetch_lname(){
        String [] columns = new String [] {DBPatient.L_NAME};
        Cursor cursor = database.query(DBPatient.DATABASE_TABLE, columns, null, null, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor fetch_phone(){
        String [] columns = new String [] {DBPatient.PNUMBER};
        Cursor cursor = database.query(DBPatient.DATABASE_TABLE, columns, null, null, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor fetchPatients() {
        return database.query(DBPatient.DATABASE_TABLE, null, null, null, null, null, null);
    }


    public int update(  String phoneNumber,String firstName, String lastName, String dateOfBirth, String gender, String email, String emergencyContact){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBPatient.F_NAME, firstName);
        contentValues.put(DBPatient.L_NAME, lastName);
        contentValues.put(DBPatient.DOB, dateOfBirth);
        contentValues.put(DBPatient.GENDER, gender);
        contentValues.put(DBPatient.PNUMBER, phoneNumber);
        contentValues.put(DBPatient.EMAIL, email);
        contentValues.put(DBPatient.E_CONTACT, emergencyContact);
        int ret = database.update(DBPatient.DATABASE_TABLE, contentValues, DBPatient.PNUMBER + "=" + phoneNumber, null);
        return ret;
    }

    public void delete (String phoneNumber){
        database.delete(DBPatient.DATABASE_TABLE, DBPatient.PNUMBER+ "=" + phoneNumber, null);
    }
}
