package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder> {

    private List<SimpleGarageDashboardActivity.Customer> customers;

    public CustomerAdapter(List<SimpleGarageDashboardActivity.Customer> customers) {
        this.customers = customers;
    }
    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_customer, parent, false);
        return new CustomerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {
        SimpleGarageDashboardActivity.Customer customer = customers.get(position);
        holder.textViewName.setText(customer.name);
        holder.textViewCar.setText(customer.car);
    }

    @Override
    public int getItemCount() {
        return customers.size();
    }

    static class CustomerViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        TextView textViewCar;

        CustomerViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewCar = itemView.findViewById(R.id.textViewCar);
        }
    }
}

