package com.example.myapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ScheduleMaintenanceActivity extends AppCompatActivity {
    private TextView tvCurrentDate, tvCurrentTime;
    private TextInputEditText etDate, etTime;
    private MaterialButton btnSchedule;
    private CardView currentScheduleCard, newScheduleCard;
    private Calendar selectedDate = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_maintenance);

        tvCurrentDate = findViewById(R.id.tvCurrentDate);
        tvCurrentTime = findViewById(R.id.tvCurrentTime);
        etDate = findViewById(R.id.etDate);
        etTime = findViewById(R.id.etTime);
        btnSchedule = findViewById(R.id.btnSchedule);
        currentScheduleCard = findViewById(R.id.currentScheduleCard);
        newScheduleCard = findViewById(R.id.newScheduleCard);

        // Animate cards
        currentScheduleCard.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in));
        newScheduleCard.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_up));

        etDate.setOnClickListener(v -> showDatePicker());
        etTime.setOnClickListener(v -> showTimePicker());

        btnSchedule.setOnClickListener(v -> scheduleNewMaintenance());

        // Set current schedule (for demonstration purposes)
        updateCurrentSchedule("15/05/2023", "10:00");
    }

    private void showDatePicker() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, year, month, dayOfMonth) -> {
                    selectedDate.set(Calendar.YEAR, year);
                    selectedDate.set(Calendar.MONTH, month);
                    selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    updateDateInView();
                },
                selectedDate.get(Calendar.YEAR),
                selectedDate.get(Calendar.MONTH),
                selectedDate.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }

    private void showTimePicker() {
        new TimePickerDialog(
                this,
                (view, hourOfDay, minute) -> {
                    selectedDate.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    selectedDate.set(Calendar.MINUTE, minute);
                    updateTimeInView();
                },
                selectedDate.get(Calendar.HOUR_OF_DAY),
                selectedDate.get(Calendar.MINUTE),
                true
        ).show();
    }

    private void updateDateInView() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        etDate.setText(sdf.format(selectedDate.getTime()));
    }

    private void updateTimeInView() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
        etTime.setText(sdf.format(selectedDate.getTime()));
    }

    private void scheduleNewMaintenance() {
        String date = etDate.getText().toString();
        String time = etTime.getText().toString();

        if (date.isEmpty() || time.isEmpty()) {
            Toast.makeText(this, "Please select both date and time", Toast.LENGTH_SHORT).show();
        } else {
            // Animate button click
            btnSchedule.startAnimation(AnimationUtils.loadAnimation(this, R.anim.button_click));

            // Simulate scheduling process
            btnSchedule.postDelayed(() -> {
                updateCurrentSchedule(date, time);
                Toast.makeText(this, "Maintenance scheduled successfully!", Toast.LENGTH_SHORT).show();
                clearFields();
            }, 200);
        }
    }

    private void updateCurrentSchedule(String date, String time) {
        tvCurrentDate.setText(date);
        tvCurrentTime.setText(time);

        // Animate updates
        tvCurrentDate.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in));
        tvCurrentTime.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in));
    }

    private void clearFields() {
        etDate.setText("");
        etTime.setText("");

        // Animate fields clearing
        etDate.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_out));
        etTime.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_out));
    }
}

