package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ServiceHistoryAdapter extends RecyclerView.Adapter<ServiceHistoryAdapter.ViewHolder> {

    private List<ServiceRecord> serviceRecords;

    public ServiceHistoryAdapter(List<ServiceRecord> serviceRecords) {
        this.serviceRecords = serviceRecords;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_service_record, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ServiceRecord record = serviceRecords.get(position);
        holder.tvServiceName.setText(record.getServiceName());
        holder.tvServiceDate.setText(record.getDate());
        holder.tvServiceCost.setText(String.format("$%.2f", record.getCost()));
    }

    @Override
    public int getItemCount() {
        return serviceRecords.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvServiceName, tvServiceDate, tvServiceCost;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvServiceName = itemView.findViewById(R.id.tvServiceName);
            tvServiceDate = itemView.findViewById(R.id.tvServiceDate);
            tvServiceCost = itemView.findViewById(R.id.tvServiceCost);
        }
    }
}

