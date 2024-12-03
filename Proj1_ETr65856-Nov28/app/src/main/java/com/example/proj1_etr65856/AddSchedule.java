
package com.example.proj1_etr65856;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
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
        EditText userAddSID = findViewById(R.id.SID);
        EditText userAddFName = findViewById(R.id.addFName);
        EditText userPNumber = findViewById(R.id.addPNumber);
        EditText userType = findViewById(R.id.addType);
        EditText userAddDate = findViewById(R.id.addDate);
        EditText userAddTime = findViewById(R.id.addTime);
        EditText userAddLocation = findViewById(R.id.addLocation);

        userAddSID.setShowSoftInputOnFocus(false);
        userAddFName.setShowSoftInputOnFocus(false);
        userPNumber.setShowSoftInputOnFocus(false);
        userType.setShowSoftInputOnFocus(false);
        userAddDate.setShowSoftInputOnFocus(false);
        userAddTime.setShowSoftInputOnFocus(false);
        userAddLocation.setShowSoftInputOnFocus(false);

        Button addBt = findViewById(R.id.addScheduleBtn);
        addBt.setOnClickListener(v -> {
            String inputDate = userAddDate.getText().toString();
            String formattedDate = formatDate(inputDate);

            dbManager.insert(userAddFName.getText().toString(),
                    userPNumber.getText().toString(),
                    userType.getText().toString(),
                    userAddTime.getText().toString(),
                    formattedDate,
                    userAddLocation.getText().toString());

            startActivity(new Intent(AddSchedule.this, DoctorSchedule.class));
        });

        Button removeBtn = findViewById(R.id.removeScheduleBtn);
        removeBtn.setOnClickListener(v -> {
            if (userAddSID.getText().toString().isEmpty()) {
                Toast.makeText(this, "Please enter a schedule ID", Toast.LENGTH_SHORT).show();
                return;
            } else {
                dbManager.delete(Long.parseLong(userAddSID.getText().toString()));
            }
            startActivity(new Intent(AddSchedule.this, DoctorSchedule.class));
        });

        ImageButton btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(view -> startActivity(new Intent(AddSchedule.this, MainActivity2.class)));
    }

    private String formatDate(String date) {
        String[] dateParts = date.split("/");

        if (dateParts.length == 3) {
            String day = removeLeadingZero(dateParts[0]);
            String month = removeLeadingZero(dateParts[1]);
            return day + "/" + month + "/" + dateParts[2];
        }
        return date;
    }

    private String removeLeadingZero(String part) {
        if (part.startsWith("0")) {
            return part.substring(1);
        }
        return part;
    }
}
