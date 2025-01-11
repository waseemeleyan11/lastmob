package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class ExpensesReportActivity extends AppCompatActivity {
    private RecyclerView rvExpenses;
    private AutoCompleteTextView spinnerCategory;
    private TextInputEditText etExpenseAmount, etNotes;
    private MaterialButton btnAddExpense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses_report);

        initializeViews();
        setupCategorySpinner();
        setupRecyclerView();
        setupClickListeners();
    }

    private void initializeViews() {
        rvExpenses = findViewById(R.id.rvExpenses);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        etExpenseAmount = findViewById(R.id.etExpenseAmount);
        etNotes = findViewById(R.id.etNotes);
        btnAddExpense = findViewById(R.id.btnAddExpense);
    }

    private void setupCategorySpinner() {
        String[] categories = new String[]{"Fuel", "Maintenance", "Insurance", "Repairs", "Other"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                categories
        );
        spinnerCategory.setAdapter(adapter);
    }

    private void setupRecyclerView() {
        rvExpenses.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupClickListeners() {
        btnAddExpense.setOnClickListener(v -> {
            if (validateInput()) {
                addNewExpense();
            }
        });
    }

    private boolean validateInput() {
        boolean isValid = true;

        if (etExpenseAmount.getText().toString().isEmpty()) {
            etExpenseAmount.setError("Amount is required");
            isValid = false;
        }

        if (spinnerCategory.getText().toString().isEmpty()) {
            spinnerCategory.setError("Category is required");
            isValid = false;
        }

        return isValid;
    }

    private void addNewExpense() {
        // Implement expense addition logic here
        Toast.makeText(this, "Expense added successfully", Toast.LENGTH_SHORT).show();
        clearInputs();
    }

    private void clearInputs() {
        etExpenseAmount.setText("");
        spinnerCategory.setText("");
        etNotes.setText("");
    }
}

