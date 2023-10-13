package com.example.travalerapp.managers;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.travalerapp.LoginActivity;
import com.example.travalerapp.MainActivity;
import com.example.travalerapp.models.logins.LoginRequestBody;
import com.example.travalerapp.models.logins.LoginResponse;
import com.example.travalerapp.models.logins.LoginService;
import android.content.Intent;
import android.widget.Toast;
import java.util.function.Consumer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginManager {

    private static LoginManager singleton;
    private LoginService loginService;
    private final String loginStateFile = "loginstate";
    private final String isLoggedInKey = "logged_in";

    public static LoginManager getInstance() {
        if (singleton == null)
            singleton = new LoginManager();

        return singleton;
    }

    private LoginManager() {
        loginService = NetworkManager.getInstance().createService(LoginService.class);
    }

    public Boolean validateCredentials(String nic, String password) {
        if (nic == null || nic.length() == 0)
            return false;

        if (password == null || password.length() == 0)
            return false;

        return true;
    }

    public void login(
            String nic,
            String password,
            Consumer<LoginResponse> onSuccess,
            Consumer<String> onError
    ){
        if (!NetworkManager.getInstance().isNetworkAvailable()){
            onError.accept("No internet connectivity");
            return;
        }

        loginService.login(new LoginRequestBody(nic, password)).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    LoginResponse loginResponse = response.body();

                    if (loginResponse != null) {
                        onSuccess.accept(loginResponse);  // Notify the caller of the success.
                    } else {
                        onError.accept("Unexpected response from server");
                    }
                } else {
                    // Convert possible HTTP error to a user-friendly message
                    onError.accept("Login failed. Please check your credentials or try again later.");
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                onError.accept("Error connecting to the server: " + t.getMessage());
            }
        });


    }

    public void setLoggedInState(boolean isLoggedIn){
        Context context = ContextManager.getInstance().getApplicationContext();
        SharedPreferences.Editor editor = context.getSharedPreferences(loginStateFile, Context.MODE_PRIVATE).edit();
        editor.putBoolean(isLoggedInKey, isLoggedIn);
        editor.apply();
    }

    public boolean getIsLoggedIn(){
        Context context = ContextManager.getInstance().getApplicationContext();
        SharedPreferences prefs = context.getSharedPreferences(loginStateFile, Context.MODE_PRIVATE);
        return prefs.getBoolean(isLoggedInKey, false);
    }

}
