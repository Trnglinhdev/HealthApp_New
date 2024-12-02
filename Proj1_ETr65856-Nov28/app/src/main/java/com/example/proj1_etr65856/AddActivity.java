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

public class AddActivity extends AppCompatActivity {

    private AddActDB dbHandler;

    DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        dbManager = new DatabaseManager(this);
        try{
            dbManager.open();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        EditText userAddFName = (EditText)findViewById(R.id.userAddFName);
        EditText userAddLName = (EditText)findViewById(R.id.userAddLName);
        EditText userAddBt = (EditText)findViewById(R.id.userAddBd);
        EditText userAddPhone = (EditText)findViewById(R.id.userAddPhone);
        EditText userAddEmail = (EditText)findViewById(R.id.userAddEmail);
        EditText userAddEContact = (EditText)findViewById(R.id.userAddEContact);
        EditText userAddGender = (EditText)findViewById(R.id.userAddGender);

        //disable soft keyboard
        userAddFName.setShowSoftInputOnFocus(false);
        userAddLName.setShowSoftInputOnFocus(false);
        userAddBt.setShowSoftInputOnFocus(false);
        userAddPhone.setShowSoftInputOnFocus(false);
        userAddEmail.setShowSoftInputOnFocus(false);
        userAddEContact.setShowSoftInputOnFocus(false);
        userAddGender.setShowSoftInputOnFocus(false);

        //set add button
        Button addBt = findViewById(R.id.addBt);
        addBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get user input and put to String
                String fName = userAddFName.getText().toString();
                String lName = userAddLName.getText().toString();
                String dateOfBirth = userAddBt.getText().toString();
                String phoneNumber = userAddPhone.getText().toString();
                String email = userAddEmail.getText().toString();
                String emergencyContact = userAddEContact.getText().toString();
                String gender = userAddGender.getText().toString();

                if(fName.isEmpty()||lName.isEmpty()||dateOfBirth.isEmpty()||phoneNumber.isEmpty()||emergencyContact.isEmpty()||email.isEmpty()||gender.isEmpty())
                {
                    Toast.makeText(AddActivity.this,"Please enter required information with *",Toast.LENGTH_SHORT).show();
                    return;
                }
                else dbManager.insert(fName, lName, dateOfBirth, gender, phoneNumber, email, emergencyContact);

                Toast.makeText(AddActivity.this, "New Patient has been added.",Toast.LENGTH_SHORT).show();
                userAddFName.setText("");
                userAddLName.setText("");
                userAddBt.setText("");
                userAddEmail.setText("");
                userAddPhone.setText("");
                userAddEContact.setText("");
                userAddGender.setText("");

                startActivity(new Intent(AddActivity.this,MainActivity2.class));
            }
        });

        //return home button
        ImageButton btnHome = (ImageButton) findViewById(R.id.btnHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddActivity.this, MainActivity2.class));
            }
        });

    }
}