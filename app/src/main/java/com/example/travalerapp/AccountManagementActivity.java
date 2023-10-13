package com.example.travalerapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.travalerapp.models.logins.LoginResponse;

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
    }

    private void displayUserData(LoginResponse loginResponse) {
        tvUserNic.setText("NIC: " + loginResponse.nic);
        tvUserName.setText("Name: " + loginResponse.name);
        tvUserEmail.setText("Email: " + loginResponse.email);
        tvStatus.setText("Status: " + loginResponse.accountStatus);
        // Set other attributes in a similar manner
    }
}
