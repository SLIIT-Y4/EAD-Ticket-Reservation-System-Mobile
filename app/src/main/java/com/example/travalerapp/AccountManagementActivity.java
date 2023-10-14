package com.example.travalerapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.travalerapp.models.logins.LoginResponse;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AccountManagementActivity extends AppCompatActivity {

    private TextView tvUserNic, tvUserName, tvUserEmail, tvStatus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_management);

        // Initialize TextViews
        tvUserNic = findViewById(R.id.nicTextView);
        tvUserName = findViewById(R.id.nameTextView);
        tvUserEmail = findViewById(R.id.emailTextView);
        tvStatus = findViewById(R.id.activeTextView);


        // Assume you're passing LoginResponse through Intent
        LoginResponse loginResponse = (LoginResponse) getIntent().getSerializableExtra("loginResponse");

        if (loginResponse != null) {
            displayUserData(loginResponse);
        }

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
    }

    private void displayUserData(LoginResponse loginResponse) {
        tvUserNic.setText("NIC: " + loginResponse.nic);
        tvUserName.setText("Name: " + loginResponse.name);
        tvUserEmail.setText("Email: " + loginResponse.email);
        tvStatus.setText("Status: " + loginResponse.accountStatus);
        // Set other attributes in a similar manner
    }
}
