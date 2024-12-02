//package com.example.proj1_etr65856;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.List;
//
//public class ConsultationAdapter extends RecyclerView.Adapter<ConsultationAdapter.ConsultationViewHolder> {
//    private List<ConsultationActivity.Consultation> consultations;
//
//    public ConsultationAdapter(List<ConsultationActivity.Consultation> consultations) {
//        this.consultations = consultations;
//    }
//
//    @Override
//    public ConsultationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.consultation_item, parent, false);
//        return new ConsultationViewHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(ConsultationViewHolder holder, int position) {
//        ConsultationActivity.Consultation consultation = consultations.get(position);
//        holder.consultationDate.setText(consultation.getConsultationDate());
//        holder.diagnosis.setText(consultation.getDiagnosis());
//        holder.treatment.setText(consultation.getTreatment());
//    }
//
//    @Override
//    public int getItemCount() {
//        return consultations.size();
//    }
//
//    public static class ConsultationViewHolder extends RecyclerView.ViewHolder {
//        public TextView consultationDate, diagnosis, treatment;
//
//        public ConsultationViewHolder(View itemView) {
//            super(itemView);
//            consultationDate = itemView.findViewById(R.id.textConsultationDate);
//            diagnosis = itemView.findViewById(R.id.conSuldiagnosis);
//            treatment = itemView.findViewById(R.id.conSultreatment);
//        }
//    }
//}
