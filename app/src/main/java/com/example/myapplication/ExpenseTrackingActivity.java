package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseTrackingActivity extends AppCompatActivity {

    private TextInputEditText etServiceType, etCost;
    private MaterialButton btnAddExpense;
    private FloatingActionButton fabRefresh;
    private PieChart pieChart;

    private Map<String, Float> expenses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etServiceType = findViewById(R.id.etServiceType);
        etCost = findViewById(R.id.etCost);
        btnAddExpense = findViewById(R.id.btnAddExpense);
        fabRefresh = findViewById(R.id.fabRefresh);
        pieChart = findViewById(R.id.pieChart);

        expenses = new HashMap<>();

        setupPieChart();
        setupListeners();
    }

    private void setupPieChart() {
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);
        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);
    }

    private void setupListeners() {
        btnAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addExpense();
            }
        });

        fabRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePieChart();
            }
        });
    }

    private void addExpense() {
        String serviceType = etServiceType.getText().toString().trim();
        String costString = etCost.getText().toString().trim();

        if (serviceType.isEmpty() || costString.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        float cost = Float.parseFloat(costString);

        if (expenses.containsKey(serviceType)) {
            expenses.put(serviceType, expenses.get(serviceType) + cost);
        } else {
            expenses.put(serviceType, cost);
        }

        etServiceType.setText("");
        etCost.setText("");

        Toast.makeText(this, "Expense added successfully", Toast.LENGTH_SHORT).show();
        updatePieChart();
    }

    private void updatePieChart() {
        List<PieEntry> entries = new ArrayList<>();
        for (Map.Entry<String, Float> entry : expenses.entrySet()) {
            entries.add(new PieEntry(entry.getValue(), entry.getKey()));
        }

        PieDataSet dataSet = new PieDataSet(entries, "Expenses");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        dataSet.setValueTextSize(12f);
        dataSet.setValueTextColor(Color.WHITE);

        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        pieChart.invalidate();

        pieChart.animateY(1000);
    }
}