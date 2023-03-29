package com.example.liftingstack.Entity;

public class User {
    private String name = "Benni";
    private String email;
    private String password;

    public User(String name, String email, String password) {

        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
