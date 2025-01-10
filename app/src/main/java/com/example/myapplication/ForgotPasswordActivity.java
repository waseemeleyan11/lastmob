package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.ActivityForgotPasswordBinding;

public class ForgotPasswordActivity extends AppCompatActivity {

    private ActivityForgotPasswordBinding binding;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharedPreferences = getSharedPreferences("AtoZGaragePrefs", MODE_PRIVATE);

        setupListeners();
    }

    private void setupListeners() {
        binding.btnResetPassword.setOnClickListener(v -> {
            if (validateInput()) {
                performPasswordReset();
            }
        });

        binding.tvBackToLogin.setOnClickListener(v -> {
            Intent intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private boolean validateInput() {
        boolean isValid = true;

        String email = binding.etEmail.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            binding.tilEmail.setError("Email is required");
            isValid = false;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilEmail.setError("Enter a valid email address");
            isValid = false;
        } else {
            binding.tilEmail.setError(null);
        }

        return isValid;
    }

    private void performPasswordReset() {
        String email = binding.etEmail.getText().toString().trim();

        // Check if the email exists in SharedPreferences
        String storedEmail = sharedPreferences.getString("email", "wm1210262@gmail.com");
        if (email.equals(storedEmail)) {
            // In a real app, you would send a password reset email here
            Toast.makeText(this, "Password reset link sent to your email", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Email not found", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}

