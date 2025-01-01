package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UpcomingMaintenanceAdapter extends RecyclerView.Adapter<UpcomingMaintenanceAdapter.ViewHolder> {

    private List<MaintenanceItem> maintenanceItems;

    public UpcomingMaintenanceAdapter(List<MaintenanceItem> maintenanceItems) {
        this.maintenanceItems = maintenanceItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_upcoming_maintenance, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MaintenanceItem item = maintenanceItems.get(position);
        holder.tvMaintenanceName.setText(item.getName());
        holder.tvMaintenanceDueDate.setText(item.getDueDate());
    }

    @Override
    public int getItemCount() {
        return maintenanceItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMaintenanceName, tvMaintenanceDueDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaintenanceName = itemView.findViewById(R.id.tvMaintenanceName);
            tvMaintenanceDueDate = itemView.findViewById(R.id.tvMaintenanceDueDate);
        }
    }
}

