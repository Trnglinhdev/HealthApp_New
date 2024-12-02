package com.example.proj1_etr65856;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;

public class ConsultationActivity extends AppCompatActivity {
    ListView listView;
    private ConsulDBManager dbManager;


//    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultation_history);

        Intent intent = getIntent();
        String fName = intent.getStringExtra("fName");
        String phone = intent.getStringExtra("phone");

        TextView p_phoneNumber = findViewById(R.id.addPhoneNumber);
        p_phoneNumber.setText(phone);

        TextView p_name = findViewById(R.id.addPName);
        p_name.setText(fName);

        dbManager = new ConsulDBManager(this);
        try {
            dbManager.open();
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }

        EditText colID = findViewById(R.id.addColID);
        TextView pName = findViewById(R.id.addPName);
        TextView pPNumber = findViewById(R.id.addPhoneNumber);
        EditText date = findViewById(R.id.addDate);
        EditText diagnosis = findViewById(R.id.addDiagnosis);
        EditText treatment = findViewById(R.id.addTreatment);


        Button addBtn = findViewById(R.id.ConsulAddBtn);
        addBtn.setOnClickListener(view -> {
            dbManager.insert(pName.getText().toString(),
                    pPNumber.getText().toString(),
                    date.getText().toString(),
                    diagnosis.getText().toString(),
                    treatment.getText().toString());
            Intent intentadd = getIntent();
            finish();
            startActivity(intentadd);
        });

        //Remove consultation
        Button removeBtn = findViewById(R.id.col_removeBtn);
        removeBtn.setOnClickListener(v -> {
            if(colID.getText().toString().isEmpty()){
                Toast.makeText(this, "Please enter a colsultation ID", Toast.LENGTH_SHORT).show();
                return;
            }
            else{
                dbManager.delete(Long.parseLong(colID.getText().toString()));

                Intent intentdelete = getIntent();
                finish();
                startActivity(intentdelete);
            }
        });

        //Home Button
        ImageButton btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(view -> startActivity(new Intent(ConsultationActivity.this, MainActivity2.class)));

        listView = findViewById(R.id.listViewConsul);
        ArrayList<String> collection = new ArrayList<>();
        Cursor cursor = dbManager.fetch_colsul(fName, phone);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String colIdList = cursor.getString(cursor.getColumnIndexOrThrow(DBColsulHistory.COLSULID));
                String dateList = cursor.getString(cursor.getColumnIndexOrThrow(DBColsulHistory.DATE));
                String diagnosisList = cursor.getString(cursor.getColumnIndexOrThrow(DBColsulHistory.DIAGNOSIS));
                String treatmentList = cursor.getString(cursor.getColumnIndexOrThrow(DBColsulHistory.TREATMENT));
                String formattedCol = String.format("Colsul ID: %s\t\tDate: %s\nDiagnosis: %s\t\tTreatment: %s\n", colIdList,dateList, diagnosisList, treatmentList);
                collection.add(formattedCol);
            } while (cursor.moveToNext());
            cursor.close();
        } else {
            Toast.makeText(this, "No consultation found for this patient.", Toast.LENGTH_SHORT).show();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, collection);
        listView.setAdapter(adapter);


    }
    @Override
    protected void onDestroy() {
        dbManager.close();
        super.onDestroy();
    }


}


