package com.example.travalerapp.models.logins;
import com.example.travalerapp.models.registers.RegisterRequestBody;
import com.example.travalerapp.models.registers.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {
    @POST("login")
    Call<LoginResponse> login(@Body LoginRequestBody request);

    @POST("signup")
    Call<RegisterResponse> register(@Body RegisterRequestBody requestBody);
}
