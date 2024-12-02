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
    ConsulDBManager colManager;


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

        colManager = new ConsulDBManager(this);
        try {
            colManager.open();
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
}}