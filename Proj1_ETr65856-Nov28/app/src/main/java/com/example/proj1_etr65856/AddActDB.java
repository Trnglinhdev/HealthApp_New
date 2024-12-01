package com.example.proj1_etr65856;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AddActDB extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "patientDB";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String TABLE_NAME = "newPatients";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    // below variable is for our first name column
    private static final String FIRST_NAME_COL = "firstName";

    // below variable is for our first name column
    private static final String LAST_NAME_COL = "lastName";

    // below variable id for our birthday column.
    private static final String BIRTHDAY_COL = "dateOfBirth";

    // below variable for our phone number column.
    private static final String PHONE_COL = "phoneNumber";

    // below variable is for our email column.
    private static final String EMAIL_COL = "email";

    // below variable for our emergency contact column.
    private static final String EMERGENCY_CONTACT_COL = "emergencyContact";

    // below variable for our gender column.
    private static final String GENDER_COL = "gender";

    // creating a constructor for our database handler.
    public AddActDB(Context context) {
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
                + BIRTHDAY_COL + " TEXT,"
                + PHONE_COL + " TEXT,"
                + EMAIL_COL + "TEXT,"
                + EMERGENCY_CONTACT_COL + "TEXT,"
                + GENDER_COL + "TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new course to our sqlite database.
    public void addNewPatients(String fName,String lName, String dateOfBirth, String phoneNumber, String email, String emergencyContact, String gender) {

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
        values.put(BIRTHDAY_COL, dateOfBirth);
        values.put(GENDER_COL,gender);
        values.put(PHONE_COL, phoneNumber);
        values.put(EMAIL_COL, email);
        values.put(EMERGENCY_CONTACT_COL, emergencyContact);

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