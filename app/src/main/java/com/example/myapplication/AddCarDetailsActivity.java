package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class AddCarDetailsActivity extends AppCompatActivity {
    private AutoCompleteTextView spinnerMake, spinnerModel, spinnerYear, spinnerFuelType;
    private TextInputEditText etMileage;
    private MaterialButton btnSubmit;
    private TextView tvCurrentCar, tvCurrentMileage, tvCurrentFuelType;
    private CardView currentCarCard, newCarCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car_details);

        spinnerMake = findViewById(R.id.spinnerMake);
        spinnerModel = findViewById(R.id.spinnerModel);
        spinnerYear = findViewById(R.id.spinnerYear);
        spinnerFuelType = findViewById(R.id.spinnerFuelType);
        etMileage = findViewById(R.id.etMileage);
        btnSubmit = findViewById(R.id.btnSubmit);
        tvCurrentCar = findViewById(R.id.tvCurrentCar);
        tvCurrentMileage = findViewById(R.id.tvCurrentMileage);
        tvCurrentFuelType = findViewById(R.id.tvCurrentFuelType);
        currentCarCard = findViewById(R.id.currentCarCard);
        newCarCard = findViewById(R.id.newCarCard);

        // Animate cards
        currentCarCard.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in));
        newCarCard.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_up));

        // Static data for current car
        tvCurrentCar.setText("Toyota Corolla (2022)");
        tvCurrentMileage.setText("Mileage: 15,000 km");
        tvCurrentFuelType.setText("Fuel Type: Gasoline");

        // Dummy data for spinners
        String[] makes = {"Toyota", "Honda", "Ford", "Chevrolet", "Nissan", "BMW", "Mercedes-Benz", "Audi"};
        String[] models = {"Corolla", "Civic", "Focus", "Malibu", "Altima", "3 Series", "C-Class", "A4"};
        String[] years = {"2023", "2022", "2021", "2020", "2019", "2018", "2017", "2016"};
        String[] fuelTypes = {"Gasoline", "Diesel", "Electric", "Hybrid", "Plug-in Hybrid"};

        ArrayAdapter<String> makeAdapter = new ArrayAdapter<>(this, R.layout.dropdown_item, makes);
        ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(this, R.layout.dropdown_item, models);
        ArrayAdapter<String> yearAdapter = new ArrayAdapter<>(this, R.layout.dropdown_item, years);
        ArrayAdapter<String> fuelTypeAdapter = new ArrayAdapter<>(this, R.layout.dropdown_item, fuelTypes);

        spinnerMake.setAdapter(makeAdapter);
        spinnerModel.setAdapter(modelAdapter);
        spinnerYear.setAdapter(yearAdapter);
        spinnerFuelType.setAdapter(fuelTypeAdapter);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String make = spinnerMake.getText().toString();
                String model = spinnerModel.getText().toString();
                String year = spinnerYear.getText().toString();
                String mileage = etMileage.getText().toString();
                String fuelType = spinnerFuelType.getText().toString();

                if (!make.isEmpty() && !model.isEmpty() && !year.isEmpty() && !mileage.isEmpty()&& !fuelType.isEmpty()) {
                    String carDetails = make + " " + model + " (" + year + ")";
                    tvCurrentCar.setText(carDetails);
                    tvCurrentMileage.setText("Mileage: " + mileage + " km");
                    tvCurrentFuelType.setText("Fuel Type: " + fuelType);

                    // Animate updates
                    tvCurrentCar.startAnimation(AnimationUtils.loadAnimation(AddCarDetailsActivity.this, R.anim.fade_in));
                    tvCurrentMileage.startAnimation(AnimationUtils.loadAnimation(AddCarDetailsActivity.this, R.anim.fade_in));
                    tvCurrentFuelType.startAnimation(AnimationUtils.loadAnimation(AddCarDetailsActivity.this, R.anim.fade_in));

                    Toast.makeText(AddCarDetailsActivity.this, "Car details updated: " + carDetails, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddCarDetailsActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

