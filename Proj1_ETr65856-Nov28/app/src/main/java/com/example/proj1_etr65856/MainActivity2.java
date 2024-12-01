package com.example.proj1_etr65856;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PatientAdapter adapter;
    private List<Patient> patientList;
    private Context context;
    DatabaseManager dbManager;
    MedDBManager medManager;
    ScheduleDBManager scheduleManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        //reference buttons
        Button addPatient = (Button) findViewById(R.id.btAdd);
        Button viewSchedule = (Button) findViewById(R.id.btSchedule);
        Button weightCalc = (Button) findViewById(R.id.btWeight);
        Button viewJoke = (Button) findViewById(R.id.btJoke);
        addPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this, AddActivity.class));
            }
        });
        viewSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this, DoctorSchedule.class));
            }
        });
        weightCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this, WeightCalculator.class));
            }
        });
        viewJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this, Jokes.class));
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        SharedPreferences sharePref = PreferenceManager.getDefaultSharedPreferences(this);
//        String fName = sharePref.getString("key1","John");
//        String lName = sharePref.getString("key2","");
//        String dob = sharePref.getString("key3","");
//        String phone = sharePref.getString("key4","");
//        String email = sharePref.getString("key5","");
//        String ec = sharePref.getString("key6","");
//        String gender = sharePref.getString("key7","");

        recyclerView = findViewById(R.id.PatientListV);

        patientList = new ArrayList<>();
        dbManager = new DatabaseManager(this);
        try {
            dbManager.open();
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }

        medManager = new MedDBManager(this);
        try {
            medManager.open();
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }

        scheduleManager = new ScheduleDBManager(this);
        try {
            scheduleManager.open();
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }


        Cursor cursor = dbManager.fetchPatients();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String fName = cursor.getString(cursor.getColumnIndexOrThrow(DBPatient.F_NAME));
                String lName = cursor.getString(cursor.getColumnIndexOrThrow(DBPatient.L_NAME));
                String phone = cursor.getString(cursor.getColumnIndexOrThrow(DBPatient.PNUMBER));
                String dob = cursor.getString(cursor.getColumnIndexOrThrow(DBPatient.DOB));
                String gender = cursor.getString(cursor.getColumnIndexOrThrow(DBPatient.GENDER));
                String email = cursor.getString(cursor.getColumnIndexOrThrow(DBPatient.EMAIL));
                String econtact = cursor.getString(cursor.getColumnIndexOrThrow(DBPatient.E_CONTACT));

//                patientList.add(new Patient(fName, lName, phone));
                patientList.add(new Patient(fName, lName, dob, gender,phone, email, econtact));

            } while (cursor.moveToNext());
            cursor.close();
        adapter = new PatientAdapter(this, patientList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        dbManager.close();


    }

//public void btnAddPressed(View v){
//    setContentView(R.layout.activity_add);
//    EditText editUserPhone = findViewById(R.id.userAddPhone);
//    EditText editUserFName = findViewById(R.id.userAddFName);
//    EditText editUserLName = findViewById(R.id.userAddLName);
//    EditText editUserBd = findViewById(R.id.userAddBd);
//    EditText editUserGender = findViewById(R.id.userAddGender);
//    EditText editUserEmail = findViewById(R.id.userAddEmail);
//    EditText editUserEContact = findViewById(R.id.userAddEContact);
//
//
//    dbManager.insert(editUserPhone.getText().toString(), editUserFName.getText().toString(),editUserLName.getText().toString(),editUserBd.getText().toString(),editUserGender.getText().toString(),editUserEmail.getText().toString(),editUserEContact.getText().toString());
//}

//    public void btnFetchOne(View v){
//        try (Cursor cursor = dbManager.fetch_fname()) { // Try-with-resources ensures cursor is closed
//            if (cursor != null && cursor.moveToFirst()) {
//                do {
//                    String ID = cursor.getString(cursor.getColumnIndexOrThrow(DBPatient.ID));
//                    String f_name = cursor.getString(cursor.getColumnIndexOrThrow(DBPatient.F_NAME));
//                    Log.i("TEST_TAG"," first name: " + f_name);
//
//                } while (cursor.moveToNext());
//            } else {
//                Log.w("TEST_TAG", "No data found in database.");
//            }
//        } catch (Exception e) {
//            Log.e("TEST_TAG", "Error while fetching data", e);
//        }
//    }

}}