package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ServiceHistoryActivity extends AppCompatActivity {

    private RecyclerView rvServiceHistory;
    private ServiceHistoryAdapter adapter;
    private List<ServiceRecord> serviceRecords;
    private TextView tvTotalServices, tvTotalCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_history);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvTotalServices = findViewById(R.id.tvTotalServices);
        tvTotalCost = findViewById(R.id.tvTotalCost);
        rvServiceHistory = findViewById(R.id.rvServiceHistory);
        FloatingActionButton fabAddService = findViewById(R.id.fabAddService);

        serviceRecords = new ArrayList<>();
        adapter = new ServiceHistoryAdapter(serviceRecords);
        rvServiceHistory.setLayoutManager(new LinearLayoutManager(this));
        rvServiceHistory.setAdapter(adapter);

        loadServiceHistory();

        fabAddService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Implement add new service functionality
                Toast.makeText(ServiceHistoryActivity.this, "Add new service clicked", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.ivFilter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Implement filter functionality
                Toast.makeText(ServiceHistoryActivity.this, "Filter clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadServiceHistory() {
        // TODO: Replace this with actual data loading from MySQL database
        serviceRecords.add(new ServiceRecord("Oil Change", "2023-05-15", 50.00));
        serviceRecords.add(new ServiceRecord("Brake Pad Replacement", "2023-04-02", 150.00));
        serviceRecords.add(new ServiceRecord("Tire Rotation", "2023-03-10", 30.00));
        serviceRecords.add(new ServiceRecord("Air Filter Replacement", "2023-02-20", 25.00));
        serviceRecords.add(new ServiceRecord("Battery Replacement", "2023-01-05", 120.00));

        adapter.notifyDataSetChanged();
        updateSummary();
    }

    private void updateSummary() {
        int totalServices = serviceRecords.size();
        double totalCost = 0;
        for (ServiceRecord record : serviceRecords) {
            totalCost += record.getCost();
        }

        tvTotalServices.setText("Total Services: " + totalServices);
        tvTotalCost.setText(String.format("Total Cost: $%.2f", totalCost));
    }
}

