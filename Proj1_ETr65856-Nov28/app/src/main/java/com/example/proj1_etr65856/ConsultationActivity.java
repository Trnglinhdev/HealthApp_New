package com.example.proj1_etr65856;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ConsultationActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ConsultationAdapter adapter;
    private List<Consultation> consultationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultation_history);

        recyclerView = findViewById(R.id.recyclerViewConsultations);
        consultationList = new ArrayList<>();

        Patient patient = new Patient("Joe", "Ma", "1990-05-20", "Female", "123-456-7890", "elin@email.com", "987-654-3210");

        consultationList.add(new Consultation(patient, "2024-01-15", "Cold and Cough", "Rest, Hydration"));
        consultationList.add(new Consultation(patient, "2024-02-20", "Routine Health Check", "General Wellness"));
        consultationList.add(new Consultation(patient, "2024-03-10", "Gastritis", "Antacids, Avoid Spicy Food"));
        consultationList.add(new Consultation(patient, "2024-04-05", "Headache", "Pain Relievers, Hydration"));
        consultationList.add(new Consultation(patient, "2024-05-15", "Back Pain", "Physical Therapy, Stretching"));
        consultationList.add(new Consultation(patient, "2024-06-01", "Flu", "Antiviral Medication, Rest"));
        consultationList.add(new Consultation(patient, "2024-07-13", "Allergy", "Antihistamines, Avoid Allergens"));




        adapter = new ConsultationAdapter(consultationList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
    public class Consultation {
        private Patient patient;
        private String consultationDate;


        private String diagnosis;
        private String treatment;


        public Consultation(Patient patient, String consultationDate, String diagnosis, String treatment) {
            this.patient = patient;
            this.consultationDate = consultationDate;
            this.diagnosis = diagnosis;
            this.treatment = treatment;
        }
        public String getPatientName() {
            return patient.getFullName();
        }

        public String getConsultationDate() {
            return consultationDate;
        }

        public String getDiagnosis() {
            return diagnosis;
        }

        public String getTreatment() {
            return treatment;
        }
    }

}


