package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupListeners();
    }

    private void setupListeners() {
        binding.btnLogin.setOnClickListener(v -> {
            if (validateInput()) {
                performLogin();
            }
        });

        binding.tvRegister.setOnClickListener(v -> {
            // TODO: Implement registration activity navigation
            Toast.makeText(LoginActivity.this, "Register clicked", Toast.LENGTH_SHORT).show();
        });
    }

    private boolean validateInput() {
        boolean isValid = true;

        String email = binding.etEmail.getText().toString().trim();
        String password = binding.etPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            binding.tilEmail.setError("Email is required");
            isValid = false;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilEmail.setError("Enter a valid email address");
            isValid = false;
        } else {
            binding.tilEmail.setError(null);
        }

        if (TextUtils.isEmpty(password)) {
            binding.tilPassword.setError("Password is required");
            isValid = false;
        } else if (password.length() < 6) {
            binding.tilPassword.setError("Password must be at least 6 characters long");
            isValid = false;
        } else {
            binding.tilPassword.setError(null);
        }

        return isValid;
    }

    private void performLogin() {
        String email = binding.etEmail.getText().toString().trim();
        String password = binding.etPassword.getText().toString().trim();

        // TODO: Implement actual login logic here
        // For demo purposes, we'll just show a toast and navigate to the main activity
//        Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//        startActivity(intent);
        finish();
    }
}

