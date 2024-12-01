package com.example.proj1_etr65856;

import android.database.Cursor;

public class Patient {
    String firstName;
    String lastName;
    String dateOfBirth;
    String gender;
    String phoneNumber;
    String email;
    String emergencyContact;

    //SharedPreferences sharePref = PreferenceManager.getDefaultSharedPreferences(this);


    public Patient(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }
    public Patient(String firstName, String lastName, String dateOfBirth, String phoneNumber, String emergencyContact) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.emergencyContact = emergencyContact;
    }

    public Patient(String firstName, String lastName, String dateOfBirth, String gender, String phoneNumber, String email, String emergencyContact) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.emergencyContact = emergencyContact;
    }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getDateOfBirth() { return dateOfBirth; }
    public String getGender() { return gender; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getEmail() { return email; }
    public String getEmergencyContact() { return emergencyContact; }
    public String getFullName(){return firstName+" "+lastName;}
}
