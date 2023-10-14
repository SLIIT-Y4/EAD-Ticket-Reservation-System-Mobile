package com.example.travalerapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.travalerapp.models.logins.LoginService;
import com.example.travalerapp.models.reservations.Reservation;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class reservationActivity extends AppCompatActivity {

    private TextView reservationTextView;
    private LoginService loginService;
    private String userNic;
    private String currentDate;

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
        bottomNavigationView.setOnItemSelectedListener(item -> {
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
                case R.id.navigation_profile:
                    if (!this.getClass().equals(AccountManagementActivity.class)) {
                        intent = new Intent(this, AccountManagementActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(intent);
                    }
                    break;
            }
            return true;
        });


//        // Initialize Retrofit
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://10.0.2.2:5000/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        LoginService LoginService = retrofit.create(com.example.travalerapp.models.logins.LoginService.class);
//
//        fetchReservations(userNic, currentDate);
    }
    private void fetchReservations(String nic, String date) {
        loginService.getReservationsByNicAndDate(nic, date).enqueue(new Callback<List<Reservation>>() {
            @Override
            public void onResponse(Call<List<Reservation>> call, Response<List<Reservation>> response) {
                if (response.isSuccessful()) {
                    List<Reservation> reservations = response.body();

                    for (Reservation reservation : reservations) {
                        // Here you can display each reservation's date in your UI.
                        // For demonstration purposes, I'm just logging the dates.
                        Log.d("Reservation Date", reservation.ReservationDate);
                    }

                } else {
                    Toast.makeText(reservationActivity.this, "Failed to fetch reservations", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Reservation>> call, Throwable t) {
                Toast.makeText(reservationActivity.this, "Error connecting to server: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}

