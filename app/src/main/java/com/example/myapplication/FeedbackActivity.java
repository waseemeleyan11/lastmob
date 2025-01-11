package com.example.myapplication;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import android.widget.RatingBar;
public class FeedbackActivity extends AppCompatActivity {
    private RatingBar ratingBar;
    private TextInputEditText etFeedback;
    private MaterialButton btnSubmitFeedback;
    private RecyclerView rvFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        initializeViews();
        setupRecyclerView();
        setupClickListeners();
    }

    private void initializeViews() {
        ratingBar = findViewById(R.id.ratingBar);
        etFeedback = findViewById(R.id.etFeedback);
        btnSubmitFeedback = findViewById(R.id.btnSubmitFeedback);
        rvFeedback = findViewById(R.id.rvFeedback);
    }

    private void setupRecyclerView() {
        rvFeedback.setLayoutManager(new LinearLayoutManager(this));
        // Set up your RecyclerView adapter here
    }

    private void setupClickListeners() {
        btnSubmitFeedback.setOnClickListener(v -> {
            if (validateInput()) {
                submitFeedback();
            }
        });

        ratingBar.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
            if (fromUser) {
                updateSubmitButton();
            }
        });
    }

    private boolean validateInput() {
        boolean isValid = true;

        if (ratingBar.getRating() == 0) {
            Toast.makeText(this, "Please provide a rating", Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        if (etFeedback.getText().toString().trim().isEmpty()) {
            etFeedback.setError("Feedback is required");
            isValid = false;
        }

        return isValid;
    }

    private void updateSubmitButton() {
        btnSubmitFeedback.setEnabled(ratingBar.getRating() > 0);
    }

    private void submitFeedback() {
        // Implement feedback submission logic here
        Toast.makeText(this, "Thank you for your feedback!", Toast.LENGTH_SHORT).show();
        clearInputs();
    }

    private void clearInputs() {
        ratingBar.setRating(0);
        etFeedback.setText("");
    }
}