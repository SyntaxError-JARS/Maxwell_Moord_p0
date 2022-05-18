package com.revature.Maxwell_Moord_p0.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Account {


    private String email;
    private String username;
    private String password;

    public Account(String email, String username, String password){
        super();
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public Account(){

    }

    //Getters And Setters
    public String getEmail(){return email;}

    public void setEmail(String email){this.email = email;}

    public String getUsername(){return username;}

    public void setUsername(String username){this.username = username;}

    public String getPassword(){return password;}

    public void setPassword(String password){this.password = password;}





    @Override // What this is?? Annotation - basically metadata
    public String toString() {
        return "User Info{" +
                "email='" + email + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

}
