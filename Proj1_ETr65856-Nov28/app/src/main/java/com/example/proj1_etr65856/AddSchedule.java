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

public class AddSchedule extends AppCompatActivity {

    private AddScheDB dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);

        //define each textview for user input
        EditText userAddFName = findViewById(R.id.userAddFNameS);
        EditText userAddLName = findViewById(R.id.userAddLNameS);
        EditText userAddDate = findViewById(R.id.userAddDateS);
        EditText userAddPhone = findViewById(R.id.userAddPhoneS);
        EditText userAddTime = findViewById(R.id.userAddTimeS);
        EditText userAddNote = findViewById(R.id.userAddNoteS);

        //disable soft keyboard
        userAddFName.setShowSoftInputOnFocus(false);
        userAddLName.setShowSoftInputOnFocus(false);
        userAddPhone.setShowSoftInputOnFocus(false);
        userAddDate.setShowSoftInputOnFocus(false);
        userAddTime.setShowSoftInputOnFocus(false);
        userAddNote.setShowSoftInputOnFocus(false);

        //dbHandler = new AddScheDB(AddSchedule.this);
        SharedPreferences sharePref = PreferenceManager.getDefaultSharedPreferences(this);

        //set add button
        Button addBt = findViewById(R.id.addBtnS);
        addBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get user input and put to String
                String fName = userAddFName.getText().toString();
                String lName = userAddLName.getText().toString();
                String date = userAddDate.getText().toString();
                String phoneNumber = userAddPhone.getText().toString();
                String time = userAddTime.getText().toString();
                String note = userAddNote.getText().toString();

                //set mandatory fields
                if(fName.isEmpty()||lName.isEmpty()||date.isEmpty()||phoneNumber.isEmpty()||time.isEmpty())
                {
                    Toast.makeText(AddSchedule.this,"Please enter required information with *",Toast.LENGTH_SHORT).show();
                    return;
                }

                //set after action that link user input to database and clear textview
                //dbHandler.addNewAppt(fName,lName,date,phoneNumber,time,note);

                SharedPreferences.Editor editor = sharePref.edit();
                editor.putString("key1",fName);
                editor.putString("key2",lName);
                editor.putString("key3",date);
                editor.putString("key4",phoneNumber);
                editor.putString("key5",time);
                editor.putString("key6",note);
                editor.commit();


                Toast.makeText(AddSchedule.this, "New Appointment has been added.",Toast.LENGTH_SHORT).show();
                userAddFName.setText("");
                userAddLName.setText("");
                userAddDate.setText("");
                userAddTime.setText("");
                userAddPhone.setText("");
                userAddNote.setText("");
            }
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