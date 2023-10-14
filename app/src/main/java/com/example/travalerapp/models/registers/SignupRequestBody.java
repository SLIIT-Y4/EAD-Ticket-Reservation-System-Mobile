package com.example.travalerapp.models.registers;

public class SignupRequestBody {
    public String nic;
    public String password;

    public SignupRequestBody(String nic,String password) {
        this.nic = nic;
        this.password = password;
    }
}