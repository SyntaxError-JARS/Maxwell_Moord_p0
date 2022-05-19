package com.revature.Maxwell_Moord_p0.web.dto;

public class LoginCreds {

    private static String username;
    private static String password;

    // JACKSON REQUIRES A NO ARG CONSTRUCTOR

    public static String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
