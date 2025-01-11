package com.example.myapplication;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;

public class UserSettingsActivity extends AppCompatActivity {
    private ShapeableImageView ivProfile;
    private TextInputEditText etName, etEmail;
    private SwitchMaterial switchNotifications, switchDarkMode, switchLocationServices;
    private MaterialButton btnChangePhoto, btnSaveSettings;
    private static final int PICK_IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);

        initializeViews();
        loadUserSettings();
        setupClickListeners();
    }

    private void initializeViews() {
        ivProfile = findViewById(R.id.ivProfile);
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        switchNotifications = findViewById(R.id.switchNotifications);
        switchDarkMode = findViewById(R.id.switchDarkMode);
        switchLocationServices = findViewById(R.id.switchLocationServices);
        btnChangePhoto = findViewById(R.id.btnChangePhoto);
        btnSaveSettings = findViewById(R.id.btnSaveSettings);
    }

    private void loadUserSettings() {
        // Load user settings from SharedPreferences or your backend
        SharedPreferences prefs = getSharedPreferences("UserSettings", MODE_PRIVATE);
        etName.setText(prefs.getString("name", ""));
        etEmail.setText(prefs.getString("email", ""));
        switchNotifications.setChecked(prefs.getBoolean("notifications", true));
        switchDarkMode.setChecked(prefs.getBoolean("darkMode", false));
        switchLocationServices.setChecked(prefs.getBoolean("location", true));
    }

    private void setupClickListeners() {
        btnChangePhoto.setOnClickListener(v -> openImagePicker());

        btnSaveSettings.setOnClickListener(v -> {
            if (validateInput()) {
                saveSettings();
            }
        });

        switchDarkMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            AppCompatDelegate.setDefaultNightMode(
                    isChecked ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO
            );
        });
    }

    private void openImagePicker() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            ivProfile.setImageURI(imageUri);
        }
    }

    private boolean validateInput() {
        boolean isValid = true;

        if (etName.getText().toString().trim().isEmpty()) {
            etName.setError("Name is required");
            isValid = false;
        }

        if (etEmail.getText().toString().trim().isEmpty()) {
            etEmail.setError("Email is required");
            isValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(etEmail.getText().toString()).matches()) {
            etEmail.setError("Invalid email address");
            isValid = false;
        }

        return isValid;
    }

    private void saveSettings() {
        SharedPreferences.Editor editor = getSharedPreferences("UserSettings", MODE_PRIVATE).edit();
        editor.putString("name", etName.getText().toString());
        editor.putString("email", etEmail.getText().toString());
        editor.putBoolean("notifications", switchNotifications.isChecked());
        editor.putBoolean("darkMode", switchDarkMode.isChecked());
        editor.putBoolean("location", switchLocationServices.isChecked());
        editor.apply();

        Toast.makeText(this, "Settings saved successfully", Toast.LENGTH_SHORT).show();
    }
}