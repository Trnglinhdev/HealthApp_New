package com.example.proj1_etr65856;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AddScheDB extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "scheduleDB";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String TABLE_NAME = "addAppointment";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    // below variable is for our first name column
    private static final String FIRST_NAME_COL = "firstName";

    // below variable is for our first name column
    private static final String LAST_NAME_COL = "lastName";

    // below variable id for our birthday column.
    private static final String DATE_COL = "date";

    // below variable for our phone number column.
    private static final String PHONE_COL = "phoneNumber";

    // below variable is for our email column.
    private static final String EMAIL_COL = "email";

    // below variable for our emergency contact column.
    private static final String TIME_COL = "time";

    // below variable for our gender column.
    private static final String NOTE_COL = "note";

    // creating a constructor for our database handler.
    public AddScheDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FIRST_NAME_COL + " TEXT,"
                + LAST_NAME_COL + " TEXT,"
                + DATE_COL + " TEXT,"
                + PHONE_COL + " TEXT,"
                + TIME_COL + "TEXT,"
                + NOTE_COL + "TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new course to our sqlite database.
    public void addNewAppt(String fName,String lName, String date, String phoneNumber, String time, String note) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(FIRST_NAME_COL, fName);
        values.put(LAST_NAME_COL, lName);
        values.put(DATE_COL, date);
        values.put(TIME_COL, time);
        values.put(PHONE_COL, phoneNumber);
        values.put(NOTE_COL, note);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}