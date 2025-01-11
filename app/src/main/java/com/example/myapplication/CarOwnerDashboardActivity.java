package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class CarOwnerDashboardActivity extends AppCompatActivity {

    private TextView tvCarModel, tvCarYear, tvMileage, tvTotalExpenses;
    private RecyclerView rvUpcomingMaintenance;
    private PieChart pieChartExpenses;
    private UpcomingMaintenanceAdapter maintenanceAdapter;
    private List<MaintenanceItem> maintenanceItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_owner_dashboard);

        initToolbar();
        initViews();
        initListeners();
        setupRecyclerView();
        loadCarDetails();
        loadUpcomingMaintenance();
        setupExpenseChart();

    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initViews() {
        tvCarModel = findViewById(R.id.tvCarModel);
        tvCarYear = findViewById(R.id.tvCarYear);
        tvMileage = findViewById(R.id.tvMileage);
        tvTotalExpenses = findViewById(R.id.tvTotalExpenses);
        rvUpcomingMaintenance = findViewById(R.id.rvUpcomingMaintenance);
        pieChartExpenses = findViewById(R.id.pieChartExpenses);
    }

    private void initListeners() {
        FloatingActionButton fabAddMaintenance = findViewById(R.id.fabAddMaintenance);
        fabAddMaintenance.setOnClickListener(this::onAddMaintenanceClicked);
        findViewById(R.id.ivNotifications).setOnClickListener(this::onNotificationsClicked);
    }

    private void onAddMaintenanceClicked(View v) {
        Toast.makeText(this, "Add new maintenance clicked", Toast.LENGTH_SHORT).show();
    }

    private void onNotificationsClicked(View v) {
        Toast.makeText(this, "Notifications clicked", Toast.LENGTH_SHORT).show();
    }

    private void setupRecyclerView() {
        maintenanceItems = new ArrayList<>();
        maintenanceAdapter = new UpcomingMaintenanceAdapter(maintenanceItems);
        rvUpcomingMaintenance.setLayoutManager(new LinearLayoutManager(this));
        rvUpcomingMaintenance.setAdapter(maintenanceAdapter);
    }

    private void loadCarDetails() {
        tvCarModel.setText("Model: Toyota Camry");
        tvCarYear.setText("Year: 2019");
        tvMileage.setText("Mileage: 35,000 miles");
    }

    private void loadUpcomingMaintenance() {
        maintenanceItems.add(new MaintenanceItem("Oil Change", "Due in 500 miles"));
        maintenanceItems.add(new MaintenanceItem("Tire Rotation", "Due in 2,000 miles"));
        maintenanceItems.add(new MaintenanceItem("Brake Inspection", "Due in 5,000 miles"));
        maintenanceAdapter.notifyDataSetChanged();
    }

    private void setupExpenseChart() {
        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(300f, "Oil Changes"));
        entries.add(new PieEntry(500f, "Tires"));
        entries.add(new PieEntry(200f, "Brakes"));
        entries.add(new PieEntry(100f, "Filters"));

        PieDataSet dataSet = new PieDataSet(entries, "Expenses");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        dataSet.setValueTextSize(12f);
        dataSet.setValueTextColor(Color.WHITE);

        PieData data = new PieData(dataSet);
        pieChartExpenses.setData(data);
        pieChartExpenses.getDescription().setEnabled(false);
        pieChartExpenses.setCenterText("Total: $1,100");
        pieChartExpenses.animateY(1000);

        tvTotalExpenses.setText("Total Expenses: $1,100");
    }
}
