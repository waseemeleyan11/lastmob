package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.ViewHolder> {

    private List<Reminder> reminders;

    public ReminderAdapter(List<Reminder> reminders) {
        this.reminders = reminders;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reminder, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Reminder reminder = reminders.get(position);
        holder.tvReminderTitle.setText(reminder.getTitle());
        holder.tvReminderDate.setText(reminder.getDate());
        holder.tvReminderFrequency.setText(reminder.getFrequency());
    }

    @Override
    public int getItemCount() {
        return reminders.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvReminderTitle, tvReminderDate, tvReminderFrequency;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvReminderTitle = itemView.findViewById(R.id.tvReminderTitle);
            tvReminderDate = itemView.findViewById(R.id.tvReminderDate);
            tvReminderFrequency = itemView.findViewById(R.id.tvReminderFrequency);
        }
    }
}

