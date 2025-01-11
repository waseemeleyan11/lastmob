package com.example.myapplication;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.android.material.switchmaterial.SwitchMaterial;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TrackOngoingServicesActivity extends AppCompatActivity {
    private SwitchMaterial switchStatus;
    private LinearProgressIndicator progressBar;
    private TextView tvStatus, tvEstimatedTime, tvStartTime;
    private CardView serviceStatusCard, serviceDetailsCard;
    private MaterialButton btnContactTechnician;
    private CountDownTimer countDownTimer;
    private long totalTimeInMillis = 60 * 60 * 1000; // 1 hour

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_services);

        switchStatus = findViewById(R.id.switchStatus);
        progressBar = findViewById(R.id.progressBar);
        tvStatus = findViewById(R.id.tvStatus);
        tvEstimatedTime = findViewById(R.id.tvEstimatedTime);
        tvStartTime = findViewById(R.id.tvStartTime);
        serviceStatusCard = findViewById(R.id.serviceStatusCard);
        serviceDetailsCard = findViewById(R.id.serviceDetailsCard);
        btnContactTechnician = findViewById(R.id.btnContactTechnician);

        // Animate cards
        serviceStatusCard.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in));
        serviceDetailsCard.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_up));

        switchStatus.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                startService();
            } else {
                completeService();
            }
        });

        btnContactTechnician.setOnClickListener(v -> {
            btnContactTechnician.startAnimation(AnimationUtils.loadAnimation(this, R.anim.button_click));
            Toast.makeText(this, "Contacting technician...", Toast.LENGTH_SHORT).show();
        });

        progressBar.setMax(100);
        progressBar.setProgress(0);
    }

    private void startService() {
        tvStatus.setText("Status: In Progress");
        tvStatus.setTextColor(getResources().getColor(R.color.colorAccent, null));
        tvStartTime.setText("Start Time: " + getCurrentTime());
        progressBar.setProgress(0);

        countDownTimer = new CountDownTimer(totalTimeInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int progress = (int) (100 - ((millisUntilFinished * 100) / totalTimeInMillis));
                progressBar.setProgress(progress);
                updateEstimatedTime(millisUntilFinished);
            }

            @Override
            public void onFinish() {
                completeService();
            }
        }.start();

        // Animate status change
        tvStatus.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in));
        tvStartTime.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in));
    }

    private void completeService() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        tvStatus.setText("Status: Completed");
        tvStatus.setTextColor(getResources().getColor(R.color.colorPrimary, null));
        progressBar.setProgress(100);
        tvEstimatedTime.setText("Estimated Time: Completed");
        switchStatus.setChecked(false);

        // Animate status change
        tvStatus.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in));
        tvEstimatedTime.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in));
    }

    private void updateEstimatedTime(long millisUntilFinished) {
        String timeLeft = String.format(Locale.getDefault(), "%02d:%02d",
                (millisUntilFinished / 1000) / 60,
                (millisUntilFinished / 1000) % 60);
        tvEstimatedTime.setText("Estimated Time: " + timeLeft);
    }

    private String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}

