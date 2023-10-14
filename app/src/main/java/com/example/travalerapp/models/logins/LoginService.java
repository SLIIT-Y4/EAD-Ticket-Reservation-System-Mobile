package com.example.travalerapp.models.logins;
import com.example.travalerapp.models.registers.RegisterRequestBody;
import com.example.travalerapp.models.registers.RegisterResponse;
import com.example.travalerapp.models.registers.SignupRequestBody;
import com.example.travalerapp.models.registers.SignupResponse;
import com.example.travalerapp.models.reservations.Reservation;

import java.util.List;

import retrofit2.http.Query;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface LoginService {
    @POST("Logins/login")
    Call<LoginResponse> login(@Body LoginRequestBody request);

    @POST("Users")
    Call<RegisterResponse> register(@Body RegisterRequestBody requestBody);

    @POST("Logins/signup")
    Call<SignupResponse> signup(@Body SignupRequestBody requestBody);

    @GET("api/Reservations/UpcomingReservation")
    Call<List<Reservation>> getReservationsByNicAndDate(@Query("nic") String nic, @Query("date") String date);
}
