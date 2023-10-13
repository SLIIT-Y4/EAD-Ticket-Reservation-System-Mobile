package com.example.travalerapp;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class TrainReservationActivity extends AppCompatActivity {

    private Spinner startSpinner, endSpinner, classSpinner, trainSpinner;
    private NumberPicker seatCountPicker;
    private EditText dateEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_reg);

        startSpinner = findViewById(R.id.startSpinner);
        endSpinner = findViewById(R.id.endSpinner);
        classSpinner = findViewById(R.id.classSpinner);
        seatCountPicker = findViewById(R.id.seatCountPicker);
        dateEditText = findViewById(R.id.dateEditText);
        trainSpinner = findViewById(R.id.TrainSpinner);

        setupSpinners();
        setupNumberPicker();
        setupDatePicker();
    }

    private void setupSpinners() {
        // Dummy data for demonstration
        String[] startStations = {"Station A", "Station B", "Station C"};
        String[] endStations = {"Station D", "Station E", "Station F"};
        String[] classes = {"First Class", "Second Class", "Third Class"};

        startSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, startStations));
        endSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, endStations));
        classSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, classes));

        startSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                updateTrainNames();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });

        endSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                updateTrainNames();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });
    }

    private void setupNumberPicker() {
        seatCountPicker.setMinValue(1);
        seatCountPicker.setMaxValue(100); // Assuming a max of 100 seats for now
    }

    private void setupDatePicker() {
        dateEditText.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);

            DatePickerDialog datePicker = new DatePickerDialog(TrainReservationActivity.this, (view, year1, month1, dayOfMonth) -> {
                dateEditText.setText(dayOfMonth + "/" + (month1 + 1) + "/" + year1);
                updateTrainNames();
            }, year, month, day);
            datePicker.show();
        });
    }

    private String[] getTrainNamesForRoute(String startStation, String endStation, String date) {
        // Here you can determine the train names based on start, end, and date.
        // This is a simple demonstration. Adjust this based on your needs.

        if ("Station A".equals(startStation) && "Station D".equals(endStation)) {
            return new String[]{"Train X","Train Y"};
        } else if ("Station B".equals(startStation) && "Station E".equals(endStation)) {
            return new String[]{"Train Y"};
        } else {
            return new String[]{"Train Z"};
        }
    }

    private void updateTrainNames() {
        String startStation = startSpinner.getSelectedItem().toString();
        String endStation = endSpinner.getSelectedItem().toString();
        String date = dateEditText.getText().toString();

        String[] availableTrains = getTrainNamesForRoute(startStation, endStation, date);
        trainSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, availableTrains));
    }

}
