package com.example.proj1_etr65856;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBPatient extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "Patient.DB";
    static final int DATABASE_VERSION = 1;

    static final String DATABASE_TABLE = "TEST";
    static final String ID = "ID";
    static final String F_NAME = "fname";
    static final String L_NAME = "lname";
    static final String DOB = "dob";
    static final String GENDER = "gender";
    static final String PNUMBER = "p_number";
    static final String EMAIL = "email";
    static final String E_CONTACT = "e_contact";

    private static final String CREATE_DB_QUERY = "CREATE TABLE " + DATABASE_TABLE + " ( "
            + PNUMBER + " INTEGER PRIMARY KEY , "
            + F_NAME + " TEXT NOT NULL, "
            + L_NAME + " TEXT NOT NULL, "
            + DOB + " TEXT NOT NULL, "
            + GENDER + " TEXT NOT NULL, "
            + EMAIL + " TEXT NOT NULL, "
            + E_CONTACT + " TEXT NOT NULL);";

    public DBPatient(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Log the CREATE TABLE query
        Log.d("CREATE_TAG", "Creating table with query: " + CREATE_DB_QUERY);
        db.execSQL(CREATE_DB_QUERY);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db); // Recreate the table
    }
}