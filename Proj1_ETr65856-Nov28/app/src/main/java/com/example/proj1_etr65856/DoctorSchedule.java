package com.example.proj1_etr65856;

import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;
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

        // Initialize the database manager
        dbManager = new ScheduleDBManager(this);
        // Open database connection
        try {
            dbManager.open();
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }

        //dbManager.insert("wer", "1421312389", "Dentist Appointment", "10:00","5/11/2024",  "Room 101");
        //dbManager.insert("Jadon", "1234123213", "Physical Exam", "12:00", "4/12/2024", "Room 123");


        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
            selectedDateText.setText("Appointments for: " + selectedDate);

            // Fetch appointments from the database for the selected date
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
                // If no appointments, display a message
                appointmentsAdapter.updateAppointments(Collections.singletonList("No appointments for this date."));
            }
        });


        // Set today's date as default
        calendarView.setDate(System.currentTimeMillis(), false, true);
    }
}

