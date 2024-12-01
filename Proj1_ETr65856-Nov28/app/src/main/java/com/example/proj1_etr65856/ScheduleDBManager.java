package com.example.proj1_etr65856;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;

public class ScheduleDBManager {
    private DBSchedule dbHelper;
    private final Context context;
    private SQLiteDatabase database;

    public ScheduleDBManager(Context ctx) {
        context = ctx;
    }

    public ScheduleDBManager open() throws SQLDataException {
        dbHelper = new DBSchedule(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String pName, String pNumber, String type, String time, String date, String location) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBSchedule.PATIENT_NAME, pName);
        contentValues.put(DBSchedule.PNUMBER, pNumber);
        contentValues.put(DBSchedule.TYPE, type);
        contentValues.put(DBSchedule.TIME, time);
        contentValues.put(DBSchedule.DATE, date);
        contentValues.put(DBSchedule.LOCATION, location);
        database.insert(DBSchedule.DATABASE_TABLE, null, contentValues);
    }

    public Cursor fetch() {
        String[] columns = new String[]{DBSchedule.SCHEDULE_ID, DBSchedule.PNUMBER, DBSchedule.PATIENT_NAME, DBSchedule.TYPE, DBSchedule.TIME, DBSchedule.DATE, DBSchedule.LOCATION};
        Cursor cursor = database.query(DBPatient.DATABASE_TABLE, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public List<Appointment> getAppointmentsForDate(String date) {
        List<Appointment> appointments = new ArrayList<>();
        String[] columns = {DBSchedule.SCHEDULE_ID, DBSchedule.PNUMBER, DBSchedule.PATIENT_NAME, DBSchedule.TYPE, DBSchedule.TIME, DBSchedule.DATE, DBSchedule.LOCATION};

        Cursor cursor = database.query(DBSchedule.DATABASE_TABLE,
                null,
                DBSchedule.DATE + " = ? ",
                new String[] {date},
                null, null, null);
        Log.d("DBQuery", "Fetching medications for: " + date);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DBSchedule.SCHEDULE_ID));
            String patientName = cursor.getString(cursor.getColumnIndexOrThrow(DBSchedule.PATIENT_NAME));
            String patientPhone = cursor.getString(cursor.getColumnIndexOrThrow(DBSchedule.PNUMBER));
            String scheduleType = cursor.getString(cursor.getColumnIndexOrThrow(DBSchedule.TYPE));
            String time = cursor.getString(cursor.getColumnIndexOrThrow(DBSchedule.TIME));
            String location = cursor.getString(cursor.getColumnIndexOrThrow(DBSchedule.LOCATION));

            appointments.add(new Appointment(id, patientName, patientPhone, scheduleType, time, location));
        }

        cursor.close();
        return appointments;
    }

    public void delete (long s_id){
        database.delete(DBSchedule.DATABASE_TABLE, DBSchedule.SCHEDULE_ID + "=" + s_id, null);
    }
}

