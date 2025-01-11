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
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
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

        // Simulate checking credentials and getting the role
        String role = getUserRole(email, password);  // You need to implement this method

        if ("admin".equals(role)) {
            Toast.makeText(this, "Login successful - Admin", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, AdminDashboardActivity.class);
            startActivity(intent);
            finish();
        } else if ("user".equals(role)) {
            Toast.makeText(this, "Login successful - User", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, UserDashboardActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show();
        }
    }

    private String getUserRole(String email, String password) {
        // TODO: Implement your logic to check credentials and return the role
        // For demonstration, returning "user" or "admin" based on email
        if ("admin@example.com".equals(email)) {
            return "admin";
        } else {
            return "user";
        }
    }
}
