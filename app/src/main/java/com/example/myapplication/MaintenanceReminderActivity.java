package com.example.myapplication;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MaintenanceReminderActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "MaintenanceReminders";
    private static final int NOTIFICATION_ID = 1;

    private SwitchMaterial switchEnableReminders, switchPushNotifications, switchEmailNotifications;
    private TextInputEditText etReminderDays, etReminderTime;
    private RecyclerView rvUpcomingReminders;
    private ReminderAdapter reminderAdapter;
    private List<Reminder> reminders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_reminder);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initViews();
        setupRecyclerView();
        loadReminders();
        createNotificationChannel();

        FloatingActionButton fabAddReminder = findViewById(R.id.fabAddReminder);
        fabAddReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewReminder();
            }
        });

        switchEnableReminders.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                enableReminders();
            } else {
                disableReminders();
            }
        });

        switchPushNotifications.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // TODO: Implement push notification setting
        });

        switchEmailNotifications.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // TODO: Implement email notification settings
        });
    }

    private void initViews() {
        switchEnableReminders = findViewById(R.id.switchEnableReminders);
        switchPushNotifications = findViewById(R.id.switchPushNotifications);
        switchEmailNotifications = findViewById(R.id.switchEmailNotifications);
        etReminderDays = findViewById(R.id.etReminderDays);
        etReminderTime = findViewById(R.id.etReminderTime);
        rvUpcomingReminders = findViewById(R.id.rvUpcomingReminders);
    }

    private void setupRecyclerView() {
        reminders = new ArrayList<>();
        reminderAdapter = new ReminderAdapter(reminders);
        rvUpcomingReminders.setLayoutManager(new LinearLayoutManager(this));
        rvUpcomingReminders.setAdapter(reminderAdapter);
    }

    private void loadReminders() {
        // TODO: Load reminders from database
        reminders.add(new Reminder("Oil Change", "2023-06-15", "Every 5,000 miles"));
        reminders.add(new Reminder("Tire Rotation", "2023-07-01", "Every 6 months"));
        reminders.add(new Reminder("Brake Inspection", "2023-08-10", "Every 12,000 miles"));
        reminderAdapter.notifyDataSetChanged();
    }

    private void addNewReminder() {
        // TODO: Implement add new reminder functionality
        Toast.makeText(this, "Add new reminder clicked", Toast.LENGTH_SHORT).show();
    }

    private void enableReminders() {
        // TODO: Implement enable reminders functionality
        scheduleNotification();
        Toast.makeText(this, "Reminders enabled", Toast.LENGTH_SHORT).show();
    }

    private void disableReminders() {
        // TODO: Implement disable reminders functionality
        cancelNotification();
        Toast.makeText(this, "Reminders disabled", Toast.LENGTH_SHORT).show();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Maintenance Reminders";
            String description = "Channel for maintenance reminders";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void scheduleNotification() {
        Intent intent = new Intent(this, NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        long futureInMillis = System.currentTimeMillis() + 60 * 1000; // Schedule for 1 minute from now (for demo purposes)
        alarmManager.set(AlarmManager.RTC_WAKEUP, futureInMillis, pendingIntent);
    }

    private void cancelNotification() {
        Intent intent = new Intent(this, NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
    }

    public static void showNotification(Context context, String title, String message) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}

