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
        String appointmentDetails = appointments.get(position);
        holder.appointmentText.setText(appointmentDetails);
    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }

    public void updateAppointments(List<String> newAppointments) {
        this.appointments = newAppointments;
        notifyDataSetChanged();
    }

    public static class AppointmentViewHolder extends RecyclerView.ViewHolder {

        TextView appointmentText;

        public AppointmentViewHolder(View itemView) {
            super(itemView);
            appointmentText = itemView.findViewById(R.id.appointmentText);
        }
    }
}



