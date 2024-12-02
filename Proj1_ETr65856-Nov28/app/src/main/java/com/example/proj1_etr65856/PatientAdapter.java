//package com.example.healthapp;
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//
//public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.ViewHolder> {
//
//    // variable for our array list and context
//    private ArrayList<Patient> PatientArrayList;
//    private Context context;
//
//    // constructor
//    public PatientAdapter(ArrayList<Patient> PatientArrayList, Context context) {
//        this.PatientArrayList = PatientArrayList;
//        this.context = context;
//    }
//
//
//    @Override
//    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
//        // on below line we are inflating our layout
//        // file for our recycler view items.
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.patient_item, parent, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder( ViewHolder holder, int position) {
//        // on below line we are setting data
//        // to our views of recycler view item.
//        Patient patient1 = PatientArrayList.get(position);
//        holder.fullNameV.setText(patient1.getFullName());
//    }
//
//    @Override
//    public int getItemCount() {
//        // returning the size of our array list
//        return PatientArrayList.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//
//        // creating variables for our text views.
//        private TextView fullNameV;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//            // initializing our text views
//            fullNameV = itemView.findViewById(R.id.fullName);
//        }
//    }
//}

package com.example.proj1_etr65856;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.ViewHolder> {
    private List<Patient> patients;
    private Context context;
    public PatientAdapter(Context context,List<Patient> patients) {
        this.context=context;
        this.patients = patients;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.patient_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Patient patient = patients.get(position);
        holder.fullName.setText(patient.getFullName());
        holder.phoneNumber.setText(patient.getPhoneNumber());
        holder.btnMedicalTrack.setOnClickListener(v -> {
            Intent intent = new Intent(context, MedicationTracking.class);
            intent.putExtra("phone", patient.getPhoneNumber());
            intent.putExtra("fName", patient.getFirstName());
            context.startActivity(intent);
        });
        holder.btnConsultationHis.setOnClickListener(v ->{
            Intent intent = new Intent(context, ConsultationActivity.class);
            intent.putExtra("phone", patient.getPhoneNumber());
            intent.putExtra("fName", patient.getFirstName());
            context.startActivity(intent);
        });
        holder.btnView.setOnClickListener(v ->{
            Intent intent = new Intent(context, ViewDetailPatient.class);
            intent.putExtra("fName", patient.getFirstName());
            intent.putExtra("lName", patient.getLastName());
            intent.putExtra("dob", patient.getDateOfBirth());
            intent.putExtra("gender", patient.getGender());
            intent.putExtra("phone", patient.getPhoneNumber());
            intent.putExtra("email", patient.getEmail());
            intent.putExtra("eContact", patient.getEmergencyContact());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return patients.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView fullName;
        TextView phoneNumber;
        Button btnMedicalTrack;
        Button btnConsultationHis;
        Button btnView;


        ViewHolder(View itemView) {
            super(itemView);
            fullName = itemView.findViewById(R.id.firstName);
            phoneNumber = itemView.findViewById(R.id.phoneNumber);
            btnMedicalTrack=itemView.findViewById(R.id.MedicalTrackBtn);
            btnConsultationHis=itemView.findViewById(R.id.ConsultationHisBtn);
            btnView=itemView.findViewById(R.id.ViewBtn);
        }
    }
}
