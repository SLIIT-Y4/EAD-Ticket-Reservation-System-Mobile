package com.example.travalerapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class reservationActivity extends AppCompatActivity {

    private TextView reservationTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation);

        reservationTextView = findViewById(R.id.reservationText);
        Button addReservationButton = findViewById(R.id.addReservationButton);

        if (addReservationButton != null) {
            addReservationButton.setOnClickListener(v -> {
                Log.d("ButtonCheck", "Attempting to start TrainReservationActivity...");
                try {
                    Toast.makeText(reservationActivity.this, "Button clicked!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(reservationActivity.this, TrainReservationActivity.class);
                    startActivity(intent);
                } catch (Exception e) {
                    Log.e("ButtonCheck", "Error starting activity", e);
                }
            });

        }

        reservationTextView.setOnClickListener(v -> {
            // Handle click event
            Toast.makeText(this, "TextView clicked!", Toast.LENGTH_SHORT).show();
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Intent intent;

            switch (item.getItemId()) {
                case R.id.navigation_reservation:
                    if (!this.getClass().equals(reservationActivity.class)) {
                        intent = new Intent(this, reservationActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(intent);
                    }
                    break;
                case R.id.navigation_history:
                    if (!this.getClass().equals(historyActivity.class)) {
                        intent = new Intent(this, historyActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(intent);
                    }
                    break;
//                case R.id.navigation_profile:
//                    if (!this.getClass().equals(AccountManagementActivity.class)) {
//                        intent = new Intent(this, AccountManagementActivity.class);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//                        startActivity(intent);
//                    }
//                    break;
            }
            return true;
        });
    }



}

