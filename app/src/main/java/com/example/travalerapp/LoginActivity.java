package com.example.travalerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travalerapp.managers.ContextManager;
import com.example.travalerapp.managers.LoginManager;


public class LoginActivity extends AppCompatActivity {


    private LoginManager loginManager;
    private EditText etUserName, etPassword;
    private Button btnLogin;
    private String nic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ContextManager.getInstance().setApplicationContext(getApplicationContext());
        loginManager = LoginManager.getInstance();


        etUserName = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        TextView registerTextView = findViewById(R.id.registerTextView);

        String text = "Don't have an account? Register";
        SpannableString spannableString = new SpannableString(text);

// Make the word "Register" clickable and underline it
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                // handle click, e.g., open the registration page
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(true); // add underline
                ds.setColor(Color.WHITE); // change clickable text color
            }
        };
        spannableString.setSpan(clickableSpan, text.indexOf("Register"), text.indexOf("Register") + "Register".length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        registerTextView.setText(spannableString);
        registerTextView.setMovementMethod(LinkMovementMethod.getInstance());
        


        btnLogin.setOnClickListener(v -> login());
        registerTextView.setOnClickListener(v -> {
            // Navigate to RegistrationActivity
            Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
            startActivity(intent);
        });


}

    private void login() {
        loginManager.login(
                etUserName.getText().toString(),
                etPassword.getText().toString(),
                loginResponse -> {
                    // Handle successful login here.
                    // For instance, navigate to another activity or show a welcome message.
                    Toast.makeText(LoginActivity.this, "Login Successful! Welcome, " + loginResponse.name, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, AccountManagementActivity.class);
                    intent.putExtra("loginResponse", loginResponse);
                    startActivity(intent);

//                    SharedPreferences sharedPref = getSharedPreferences("user_data", Context.MODE_PRIVATE);
//                    SharedPreferences.Editor editor = sharedPref.edit();
//
//                    editor.putString("user_nic", nic);  // Assuming `nic` is the variable where you have user's NIC.
//                    editor.apply();

                },
                error -> {
                    // Handle login errors here.
                    Toast.makeText(LoginActivity.this, error, Toast.LENGTH_LONG).show();
                }
        );

    }


    private void handleLoginFailed(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    private void handleLoginSuccessful() {
       // loginManager.setLoggedInState(true);
        // TODO: Move to Main Activity
    }
}