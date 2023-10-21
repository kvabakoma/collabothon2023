package com.tigerchamp.collabothon2023backend.model.binding;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserLogin {
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;

    public UserLogin() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
