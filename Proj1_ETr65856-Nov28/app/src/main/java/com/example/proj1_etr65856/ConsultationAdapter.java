package com.example.proj1_etr65856;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ConsultationAdapter extends RecyclerView.Adapter<ConsultationAdapter.ViewHolder> {
    private List<ConsultationActivity.Consultation> consultations;  // Use Consultation class here directly

    public ConsultationAdapter(List<ConsultationActivity.Consultation> consultations) {
        this.consultations = consultations;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.consultation_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ConsultationActivity.Consultation consultation = consultations.get(position);
        holder.textPatientName.setText(consultation.getPatientName());
        holder.textConsultationDate.setText(consultation.getConsultationDate());
        holder.conSuldiagnosis.setText(consultation.getDiagnosis());
        holder.conSultreatment.setText(consultation.getTreatment());
    }

    @Override
    public int getItemCount() {
        return consultations.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textPatientName;
        TextView textConsultationDate;
        TextView conSuldiagnosis;
        TextView conSultreatment;


        ViewHolder(View itemView) {
            super(itemView);
            textPatientName = itemView.findViewById(R.id.textPatientName);
            textConsultationDate = itemView.findViewById(R.id.textConsultationDate);
            conSuldiagnosis = itemView.findViewById(R.id.conSuldiagnosis);
            conSultreatment = itemView.findViewById(R.id.conSultreatment);
        }
    }
}
