package com.tigerchamp.collabothon2023backend.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserLoginDto {
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;

    public UserLoginDto() {
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
