package com.example.proj1_etr65856;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.nio.channels.Pipe;

public class MedTrackDB  extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "medTrackDB";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String TABLE_NAME = "medicationTracking";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    // below variable is for our pills name column
    private static final String PILLS_NAME_COL = "pillsName";

    // below variable is for our amount column
    private static final String AMOUNT_COL = "amount";

    // below variable id for our duration column.
    private static final String DURATION_COL = "duration";


    // creating a constructor for our database handler.
    public MedTrackDB(Context context) {
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
                + PILLS_NAME_COL + " TEXT,"
                + AMOUNT_COL + " TEXT,"
                + DURATION_COL+ "TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new course to our sqlite database.
    public void medTrack(String userAddNamePN, String userAddAm, String userAddDu) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(PILLS_NAME_COL, userAddNamePN);
        values.put(AMOUNT_COL, userAddAm);
        values.put(DURATION_COL, userAddDu);

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
