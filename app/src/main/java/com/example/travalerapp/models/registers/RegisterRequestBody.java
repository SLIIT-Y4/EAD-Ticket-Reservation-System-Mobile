package com.example.travalerapp.models.registers;

public class RegisterRequestBody {
    public String nic;
    public String name;
    public String email;
    public String password;
    public String confirmPassword;
    public String UserRole;

    public RegisterRequestBody(String nic, String name, String email, String password, String confirmPassword) {
        this.nic = nic;
        this.name = name;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.UserRole = "Traveler"; // This is hardcoded as per your requirement
    }
}