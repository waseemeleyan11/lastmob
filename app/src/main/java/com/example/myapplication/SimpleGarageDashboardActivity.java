package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SimpleGarageDashboardActivity extends AppCompatActivity {

    private TextView textViewPendingServices;
    private TextView textViewCompletedServices;
    private RecyclerView recyclerViewCustomers;
    private CustomerAdapter customerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_garage_dashboard);

        initViews();
        setupRecyclerView();
        updateSummary();
    }

    private void initViews() {
        textViewPendingServices = findViewById(R.id.textViewPendingServices);
        textViewCompletedServices = findViewById(R.id.textViewCompletedServices);
        recyclerViewCustomers = findViewById(R.id.recyclerViewCustomers);
    }

    private void setupRecyclerView() {
        recyclerViewCustomers.setLayoutManager(new LinearLayoutManager(this));
        List<Customer> customers = getDummyCustomers();
        customerAdapter = new CustomerAdapter(customers);
        recyclerViewCustomers.setAdapter(customerAdapter);
    }

    private void updateSummary() {
        int pendingServices = 5; // Replace with actual data
        int completedServices = 3; // Replace with actual data

        textViewPendingServices.setText("Pending Services: " + pendingServices);
        textViewCompletedServices.setText("Completed Services: " + completedServices);
    }

    private List<Customer> getDummyCustomers() {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("John Doe", "Toyota Camry"));
        customers.add(new Customer("Jane Smith", "Honda Civic"));
        customers.add(new Customer("Bob Johnson", "Ford F-150"));
        return customers;
    }

    static class Customer {
        String name;
        String car;

        Customer(String name, String car) {
            this.name = name;
            this.car = car;
        }
    }
}

