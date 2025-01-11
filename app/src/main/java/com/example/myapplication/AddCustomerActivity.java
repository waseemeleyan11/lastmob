package com.example.myapplication;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class AddCustomerActivity extends AppCompatActivity {
    private TextInputEditText etName, etContact, etAddress;
    private MaterialButton btnSubmit;
    private CardView customerCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        etName = findViewById(R.id.etName);
        etContact = findViewById(R.id.etContact);
        etAddress = findViewById(R.id.etAddress);
        btnSubmit = findViewById(R.id.btnSubmit);
        customerCard = findViewById(R.id.customerCard);

        // Animate card appearance
        customerCard.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in));

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString().trim();
                String contact = etContact.getText().toString().trim();
                String address = etAddress.getText().toString().trim();

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(contact) || TextUtils.isEmpty(address)) {
                    Toast.makeText(AddCustomerActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                } else {
                    btnSubmit.startAnimation(AnimationUtils.loadAnimation(AddCustomerActivity.this, R.anim.button_click));

                    btnSubmit.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(AddCustomerActivity.this, "Customer added successfully!", Toast.LENGTH_SHORT).show();
                            clearFields();
                        }
                    }, 200);
                }
            }
        });
    }

    private void clearFields() {
        etName.setText("");
        etContact.setText("");
        etAddress.setText("");

        // Animate fields clearing
        etName.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_out));
        etContact.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_out));
        etAddress.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_out));

        // Bring focus back to the name field
        etName.requestFocus();
    }
}

