package com.example.proj1_etr65856;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.sql.SQLDataException;
import java.util.ArrayList;

public class ViewDetailPatient extends AppCompatActivity {

    DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view);

        Intent intent = getIntent();
        String fName = intent.getStringExtra("fName");
        String lName = intent.getStringExtra("lName");
        String dob = intent.getStringExtra("dob");
        String gender = intent.getStringExtra("gender");
        String phone = intent.getStringExtra("phone");
        String email = intent.getStringExtra("email");
        String eContact = intent.getStringExtra("eContact");

        EditText firstName = findViewById(R.id.firstName);
        EditText lastName = findViewById(R.id.lastName);
        EditText dobView = findViewById(R.id.dob);
        EditText genderView = findViewById(R.id.gender);
        TextView phoneNumber = findViewById(R.id.phoneNumber);
        EditText emailView = findViewById(R.id.email);
        EditText emergencyContactView = findViewById(R.id.emergencyContact);


        firstName.setText(fName);
        lastName.setText(lName);
        dobView.setText(dob);
        genderView.setText(gender);
        phoneNumber.setText(phone);
        emailView.setText(email);
        emergencyContactView.setText(eContact);

        dbManager = new DatabaseManager(this);
        try {
            dbManager.open();
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }

        Button updateBtn = findViewById(R.id.updateBtn);
        updateBtn.setOnClickListener(v -> {
            dbManager.update(phoneNumber.getText().toString(),
                    firstName.getText().toString(),
                    lastName.getText().toString(),
                    dobView.getText().toString(),
                    genderView.getText().toString(),
                    emailView.getText().toString(),
                    emergencyContactView.getText().toString());
            //go back to main
            startActivity(new Intent(ViewDetailPatient.this,MainActivity2.class));
        });

        Button cancelBtn = findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(v -> {
            startActivity(new Intent(ViewDetailPatient.this,MainActivity2.class));
        });

        Button removeBtn = findViewById(R.id.removeBtn);
        removeBtn.setOnClickListener(v -> {
            dbManager.delete(phoneNumber.getText().toString());
            startActivity(new Intent(ViewDetailPatient.this,MainActivity2.class));
        });
    }
}
