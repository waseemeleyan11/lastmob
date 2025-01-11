package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdminDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_dashboard_activity);
    }

    public void onButtonClick(View view) {
        Intent intent = null;
        int id = view.getId();

        if (id == R.id.btnAddCarDetails) {
            intent = new Intent(this, AddCarDetailsActivity.class);
        } else if (id == R.id.btnScheduleMaintenance) {
            intent = new Intent(this, ScheduleMaintenanceActivity.class);
        } else if (id == R.id.btnTrackOngoingServices) {
            intent = new Intent(this, TrackOngoingServicesActivity.class);
        } else if (id == R.id.btnExpensesReport) {
            intent = new Intent(this, ExpensesReportActivity.class);
        } else if (id == R.id.btnFeedback) {
            intent = new Intent(this, FeedbackActivity.class);
        } else if (id == R.id.btnUserSettings) {
            intent = new Intent(this, UserSettingsActivity.class);
        } else if (id == R.id.btnHelpSupport) {
            intent = new Intent(this, HelpSupportActivity.class);
        }

        if (intent != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "Feature coming soon!", Toast.LENGTH_SHORT).show();
        }
    }

}