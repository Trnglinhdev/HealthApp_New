package com.example.proj1_etr65856;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class DoctorSchedule extends AppCompatActivity {

    private TextView selectedDateText;
    private RecyclerView appointmentsRecyclerView;
    private AppointmentAdapter appointmentsAdapter;
    private HashMap<String, List<String>> appointmentsData;
    private ScheduleDBManager dbManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule);

        selectedDateText = findViewById(R.id.monthYearText);
        CalendarView calendarView = findViewById(R.id.calendarView);
        appointmentsRecyclerView = findViewById(R.id.appointmentsRecyclerView);

        appointmentsRecyclerView = findViewById(R.id.appointmentsRecyclerView);
        appointmentsAdapter = new AppointmentAdapter(new ArrayList<>());
        appointmentsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        appointmentsRecyclerView.setAdapter(appointmentsAdapter);

        dbManager = new ScheduleDBManager(this);
        try {
            dbManager.open();
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }

        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            String selectedDate = (month + 1)+ "/" + dayOfMonth+ "/" + year;
            selectedDateText.setText("Appointments for: " + selectedDate);
            List<Appointment> appointments = dbManager.getAppointmentsForDate(selectedDate);
            Log.d("DoctorSchedule", "Selected Date: " + selectedDate);
            Log.d("DoctorSchedule", "Appointments Count: " + (appointments != null ? appointments.size() : 0));

            if (appointments != null && !appointments.isEmpty()) {
                List<String> appointmentDetails = new ArrayList<>();
                for (Appointment appointment : appointments) {
                    String details = "ID: " + appointment.getId()
                            + " \t\t Name: " + appointment.getPatientName()
                            + "\nPhone #: " + appointment.getPatientPhone()
                            + "\nType: " + appointment.getScheduleType()
                            + "\nTime: " + appointment.getTime()
                            + " \t\t Location:" + appointment.getLocation();
                    appointmentDetails.add(details);
                }
                appointmentsAdapter.updateAppointments(appointmentDetails);
            } else {
                appointmentsAdapter.updateAppointments(Collections.singletonList("No appointments for this date."));
            }
        });
        calendarView.setDate(System.currentTimeMillis(), false, true);

        Button arBtn = findViewById(R.id.addremoveBtn);
        arBtn.setOnClickListener(v -> {
            startActivity(new Intent(DoctorSchedule.this, AddSchedule.class));
        });
        ImageButton btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(view -> startActivity(new Intent(DoctorSchedule.this, MainActivity2.class)));
    }

}


