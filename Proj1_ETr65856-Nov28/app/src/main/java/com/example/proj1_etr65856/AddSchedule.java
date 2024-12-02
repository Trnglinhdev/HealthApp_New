package com.example.proj1_etr65856;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.sql.SQLDataException;

public class AddSchedule extends AppCompatActivity {

    private ScheduleDBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);

        dbManager = new ScheduleDBManager(this);
        try {
            dbManager.open();
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }

        //define each textview for user input
        EditText userAddSID = findViewById(R.id.SID);
        EditText userAddFName = findViewById(R.id.addFName);
        EditText userPNumber = findViewById(R.id.addPNumber);
        EditText userType = findViewById(R.id.addType);
        EditText userAddDate = findViewById(R.id.addDate);
        EditText userAddTime = findViewById(R.id.addTime);
        EditText userAddLocation = findViewById(R.id.addLocation);

        //disable soft keyboard
        userAddSID.setShowSoftInputOnFocus(false);
        userAddFName.setShowSoftInputOnFocus(false);
        userPNumber.setShowSoftInputOnFocus(false);
        userType.setShowSoftInputOnFocus(false);
        userAddDate.setShowSoftInputOnFocus(false);
        userAddTime.setShowSoftInputOnFocus(false);
        userAddLocation.setShowSoftInputOnFocus(false);

        //set add button
        Button addBt = findViewById(R.id.addScheduleBtn);
        addBt.setOnClickListener(v -> {
            //dbManager.insert("Jadon", "1234123213", "Physical Exam", "12:00", "4/12/2024", "Room 123");
            //appointment date need to be no "0". Ex: 12/7/2024
            dbManager.insert(userAddFName.getText().toString(),
                    userPNumber.getText().toString(),
                    userType.getText().toString(),
                    userAddTime.getText().toString(),
                    userAddDate.getText().toString(),
                    userAddLocation.getText().toString());

            startActivity(new Intent(AddSchedule.this, DoctorSchedule.class));
        });

        Button removeBtn = findViewById(R.id.removeScheduleBtn);
        removeBtn.setOnClickListener(v -> {
            if(userAddSID.getText().toString().isEmpty()){
                Toast.makeText(this, "Please enter a schedule ID", Toast.LENGTH_SHORT).show();
                return;
            }
            else{
                dbManager.delete(Long.parseLong(userAddSID.getText().toString()));
            }
            startActivity(new Intent(AddSchedule.this, DoctorSchedule.class));
        });

        //return home button
        ImageButton btnHome = (ImageButton) findViewById(R.id.btnHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddSchedule.this, MainActivity2.class));
            }
        });
    }
}