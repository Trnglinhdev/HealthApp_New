package com.example.proj1_etr65856;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder> {

    private List<String> appointments;

    // Constructor
    public AppointmentAdapter(List<String> appointments) {
        this.appointments = appointments;
    }

    @Override
    public AppointmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the item layout for each appointment
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.appointment_item, parent, false);
        return new AppointmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AppointmentViewHolder holder, int position) {
        // Bind the appointment data to the view
        String appointmentDetails = appointments.get(position);
        holder.appointmentText.setText(appointmentDetails);
    }

    @Override
    public int getItemCount() {
        return appointments.size(); // Return the size of the appointments list
    }

    // Method to update the list of appointments
    public void updateAppointments(List<String> newAppointments) {
        this.appointments = newAppointments;
        notifyDataSetChanged(); // Notify the adapter that the data has changed
    }

    // ViewHolder class
    public static class AppointmentViewHolder extends RecyclerView.ViewHolder {

        TextView appointmentText;

        public AppointmentViewHolder(View itemView) {
            super(itemView);
            // Initialize the TextView
            appointmentText = itemView.findViewById(R.id.appointmentText);
        }
    }
}



