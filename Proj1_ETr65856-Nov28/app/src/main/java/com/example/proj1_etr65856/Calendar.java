package com.example.proj1_etr65856;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Calendar extends AppCompatActivity {
    private TextView monthYearText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule);

        monthYearText = findViewById(R.id.monthYearText);
        CalendarView calendarView = findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
            monthYearText.setText("Selected Date: " + selectedDate);
        });
    }
}


