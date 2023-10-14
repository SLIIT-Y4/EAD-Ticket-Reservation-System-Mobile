package com.example.travalerapp.managers;

import android.content.Context;

import com.example.travalerapp.RegistrationActivity;
import com.example.travalerapp.models.registers.RegisterRequestBody;
import com.example.travalerapp.models.registers.RegisterResponse;
import com.example.travalerapp.models.logins.LoginService;
import com.example.travalerapp.models.registers.SignupRequestBody;
import com.example.travalerapp.models.registers.SignupResponse;

import java.util.function.Consumer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupManager {

    private static SignupManager singleton;
    private LoginService registerService;

    public static SignupManager getInstance() {
        if (singleton == null)
            singleton = new SignupManager();

        return singleton;
    }

    private SignupManager() {
        registerService = NetworkManager.getInstance().createService(LoginService.class);
    }

    public Boolean validateRegistrationDetails(String nic, String password) {
        // You can add more validations here
        return nic != null && password != null;
    }

    public void signup(
            String nic,
            String password,
            Consumer<SignupResponse> onSuccess,
            Consumer<String> onError
    ){
        if (!NetworkManager.getInstance().isNetworkAvailable()){
            onError.accept("No internet connectivity");
            return;
        }

        SignupRequestBody requestBody = new SignupRequestBody(nic, password);
        registerService.signup(requestBody).enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                if (response.isSuccessful()) {
                    SignupResponse signupResponse = response.body();

                    if (signupResponse != null) {
                        onSuccess.accept(signupResponse);  // Notify the caller of the success.
                    } else {
                        onError.accept("Unexpected response from server");
                    }
                } else {
                    onError.accept("Registration failed. Please check your details or try again later.");
                }
            }

            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {
                onError.accept("Error connecting to the server: " + t.getMessage());
            }
        });
    }
}


