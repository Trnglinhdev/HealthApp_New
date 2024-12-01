package com.example.proj1_etr65856;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLDataException;
import java.util.ArrayList;

public class MedicationTracking extends AppCompatActivity {
    ListView listView;
    private MedDBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication_tracking);

        Intent intent = getIntent();
        String fName = intent.getStringExtra("fName");
        String phone = intent.getStringExtra("phone");

        TextView p_phoneNumber = findViewById(R.id.addPhoneNumber);
        p_phoneNumber.setText(phone);

        TextView p_name = findViewById(R.id.addPName);
        p_name.setText(fName);

        dbManager = new MedDBManager(this);
        try {
            dbManager.open();  // Open the database
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }

        EditText medID = findViewById(R.id.addMedID);
        TextView pName = findViewById(R.id.addPName);
        TextView pPNumber = findViewById(R.id.addPhoneNumber);
        EditText medName = findViewById(R.id.addMedName);
        EditText quantity = findViewById(R.id.addQuantity);
        EditText date = findViewById(R.id.addDate);
        EditText duration = findViewById(R.id.addDuration);

        //add medication
        Button addBtn = findViewById(R.id.logBt);
        addBtn.setOnClickListener(view -> {
            dbManager.insert(pName.getText().toString(),
                    pPNumber.getText().toString(),
                    medName.getText().toString(),
                    quantity.getText().toString(),
                    date.getText().toString(),
                    duration.getText().toString());

            startActivity(new Intent(MedicationTracking.this, MainActivity2.class));
        });

        //Remove medication
        Button removeBtn = findViewById(R.id.med_removeBtn);
        removeBtn.setOnClickListener(v -> {
            dbManager.delete(Long.parseLong(medID.getText().toString()));
            startActivity(new Intent(MedicationTracking.this, MainActivity2.class));
        });

        //Home Button
        ImageButton btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(view -> startActivity(new Intent(MedicationTracking.this, MainActivity2.class)));

        //Med List
        listView = findViewById(R.id.listView);
        ArrayList<String> collection = new ArrayList<>();
        Cursor cursor = dbManager.fetch_med(fName, phone);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String medIdList = cursor.getString(cursor.getColumnIndexOrThrow(DBMedTrack.MEDID));
                String medNameList = cursor.getString(cursor.getColumnIndexOrThrow(DBMedTrack.MED_NAME));
                String quantityList = cursor.getString(cursor.getColumnIndexOrThrow(DBMedTrack.QUANTITY));
                String durationList = cursor.getString(cursor.getColumnIndexOrThrow(DBMedTrack.DURATION));
                String dateList = cursor.getString(cursor.getColumnIndexOrThrow(DBMedTrack.DATE));

                String formattedMed = String.format("Med ID: %s\t\tMed Name: %s\nDate: %s\t\tQuantity: %s\t\tDuration: %s\n", medIdList, medNameList,dateList, quantityList, durationList);
                collection.add(formattedMed);
            } while (cursor.moveToNext());
            cursor.close();
        } else {
            Toast.makeText(this, "No medications found for this patient.", Toast.LENGTH_SHORT).show();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, collection);
        listView.setAdapter(adapter);

    }

    @Override
    protected void onDestroy() {
        dbManager.close();  // Close database when activity is destroyed
        super.onDestroy();
    }
}