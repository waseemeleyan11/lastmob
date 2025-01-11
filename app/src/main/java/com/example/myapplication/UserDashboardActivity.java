package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.example.myapplication.R;


import androidx.appcompat.app.AppCompatActivity;

public class UserDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_dashboard_activity);
    }

    public void onButtonClick(View view) {
        Intent intent = null;
        int id = view.getId();

        if (id == R.id.btnExpenseTracking) {
            intent = new Intent(this, ExpenseTrackingActivity.class);
        } else if (id == R.id.btnServiceHistory) {
            intent = new Intent(this, ServiceHistoryActivity.class);
        } else if (id == R.id.btnCarOwnerDashboard) {
            intent = new Intent(this, CarOwnerDashboardActivity.class);
        } else if (id == R.id.btnMaintenanceReminder) {
            intent = new Intent(this, MaintenanceReminderActivity.class);
        } else if (id == R.id.btnSimpleGarageDashboard) {
            intent = new Intent(this, SimpleGarageDashboardActivity.class);
        } else if (id == R.id.btnAddCustomer) {
            intent = new Intent(this, AddCustomerActivity.class);
        }

        if (intent != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "Feature coming soon!", Toast.LENGTH_SHORT).show();
        }
    }

}