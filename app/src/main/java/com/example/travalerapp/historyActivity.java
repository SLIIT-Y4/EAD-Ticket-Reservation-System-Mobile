package com.example.travalerapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class historyActivity extends AppCompatActivity {

    private TextView nicTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);

        nicTextView = findViewById(R.id.nicTextView);
        Button editProfileButton = findViewById(R.id.editProfileButton);




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


    }}
