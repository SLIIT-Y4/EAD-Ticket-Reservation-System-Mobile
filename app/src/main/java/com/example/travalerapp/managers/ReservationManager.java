package com.example.travalerapp.managers;

import com.example.travalerapp.models.logins.LoginService;

import java.util.Date;

public class ReservationManager {
    private static ReservationManager singleton;
    private LoginService reservationService;

    public static ReservationManager getInstance() {
        if (singleton == null)
            singleton = new ReservationManager();

        return singleton;
    }

    private ReservationManager() {
        reservationService = NetworkManager.getInstance().createService(LoginService.class);
    }

    public Boolean validateRegistrationDetails(String UserNIC, Date CreateDate) {
        // You can add more validations here
        return UserNIC != null && CreateDate != null;
    }


}
