package com.example.travalerapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.travalerapp.managers.RegisterManager;
import com.example.travalerapp.managers.SignupManager;
import com.example.travalerapp.models.registers.RegisterRequestBody;
import com.example.travalerapp.models.registers.RegisterResponse;
import com.example.travalerapp.models.registers.SignupResponse;

import java.util.function.Consumer;

public class RegistrationActivity extends AppCompatActivity {

    private EditText nicEditText, nameEditText, emailEditText, passwordEditText, confirmPasswordEditText;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        nicEditText = findViewById(R.id.nicEditText);
        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);
        registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
                signupUser();
            }
        });
    }

    private void registerUser() {
        String nic = nicEditText.getText().toString();
        String name = nameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String confirmPassword = confirmPasswordEditText.getText().toString();

        RegisterRequestBody requestBody = new RegisterRequestBody(nic, name, email, password, confirmPassword);

        RegisterManager.getInstance().register(nic, name, email, password, confirmPassword, new Consumer<RegisterResponse>() {
            @Override
            public void accept(RegisterResponse registerResponse) {
                Toast.makeText(RegistrationActivity.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                // Maybe start another activity or perform some action?
            }
        }, new Consumer<String>() {
            @Override
            public void accept(String error) {
                Toast.makeText(RegistrationActivity.this, error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void signupUser() {
        String nic = nicEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        SignupManager.getInstance().signup(nic, password, new Consumer<SignupResponse>() {
            @Override
            public void accept(SignupResponse signupResponse) {
                Toast.makeText(RegistrationActivity.this, "Signup Successful!", Toast.LENGTH_SHORT).show();
                // Maybe start another activity or perform some action?
            }
        }, new Consumer<String>() {
            @Override
            public void accept(String error) {
                Toast.makeText(RegistrationActivity.this, error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
