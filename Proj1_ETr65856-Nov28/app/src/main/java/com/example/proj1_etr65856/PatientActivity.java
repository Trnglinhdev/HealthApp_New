//package com.example.proj1_etr65856;
//
//import android.content.Context;
//import android.os.Bundle;
//import android.widget.Button;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class PatientActivity extends AppCompatActivity {
//    private RecyclerView recyclerView;
//    private PatientAdapter adapter;
//    private List<Patient> patientList;
//    private Context context;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        recyclerView = findViewById(R.id.PatientListV);
//
//        patientList = new ArrayList<>();
//        patientList.add(new Patient("Joe"));
//        patientList.add(new Patient("Hi"));
//        patientList.add(new Patient("Joe"));
//        patientList.add(new Patient("Ki"));
//        patientList.add(new Patient("Xi"));
//        adapter = new PatientAdapter(this, patientList);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(adapter);
//    }
//
//
//}
//
//
