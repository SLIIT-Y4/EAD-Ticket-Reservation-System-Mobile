package com.example.travalerapp.models.logins;
import java.io.Serializable;

public class LoginResponse implements Serializable {
    public String id;
    public String name;
    public String nic;
    public String email;
    public String userRole;
    public String password;
    public String confirmPassword;
    public String accountStatus;
}
