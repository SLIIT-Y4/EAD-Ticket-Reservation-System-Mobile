package com.example.travalerapp.managers;

import android.content.Context;

import com.example.travalerapp.RegistrationActivity;
import com.example.travalerapp.models.registers.RegisterRequestBody;
import com.example.travalerapp.models.registers.RegisterResponse;
import com.example.travalerapp.models.logins.LoginService;

import java.util.function.Consumer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterManager {

    private static RegisterManager singleton;
    private LoginService registerService;

    public static RegisterManager getInstance() {
        if (singleton == null)
            singleton = new RegisterManager();

        return singleton;
    }

    private RegisterManager() {
        registerService = NetworkManager.getInstance().createService(LoginService.class);
    }

    public Boolean validateRegistrationDetails(String nic, String name, String email, String password, String confirmPassword) {
        // You can add more validations here
        return nic != null && name != null && email != null && password != null && confirmPassword != null;
    }

    public void register(
            String nic,
            String name,
            String email,
            String password,
            String confirmPassword,
            Consumer<RegisterResponse> onSuccess,
            Consumer<String> onError
    ){
        if (!NetworkManager.getInstance().isNetworkAvailable()){
            onError.accept("No internet connectivity");
            return;
        }

        RegisterRequestBody requestBody = new RegisterRequestBody(nic, name, email, password, confirmPassword);
        registerService.register(requestBody).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()) {
                    RegisterResponse registerResponse = response.body();

                    if (registerResponse != null) {
                        onSuccess.accept(registerResponse);  // Notify the caller of the success.
                    } else {
                        onError.accept("Unexpected response from server");
                    }
                } else {
                    onError.accept("Registration failed. Please check your details or try again later.");
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                onError.accept("Error connecting to the server: " + t.getMessage());
            }
        });
    }
}
