package com.example.proj1_etr65856;

public class Appointment {
    private int id;
    private String patientName;
    private String patientPhone;
    private String scheduleType;
    private String time;
    private String location;

    public Appointment(int id, String patientName, String patientPhone, String scheduleType, String time, String location) {
        this.id = id;
        this.patientName = patientName;
        this.patientPhone = patientPhone;
        this.scheduleType = scheduleType;
        this.time = time;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getPatientPhone() {
        return patientPhone;
    }

    public String getScheduleType() {
        return scheduleType;
    }

    public String getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }
}