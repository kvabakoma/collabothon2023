package com.tigerchamp.collabothon2023backend.model.dto;

import com.tigerchamp.collabothon2023backend.model.Constants;
import com.tigerchamp.collabothon2023backend.model.entity.Role;
import com.tigerchamp.collabothon2023backend.model.entity.User;

import java.util.Set;

public class AuthenticatedUserDto {
    private String token;
    private final String type;
    private String id;
    private String username;
    private Set<Role> roles;

    public AuthenticatedUserDto(User loggedIn, String token) {
        this.id = (loggedIn != null && loggedIn.getId() != null) ? loggedIn.getId() : null;
        this.type = Constants.AUTH_TYPE;
        this.username = (loggedIn != null && loggedIn.getUsername() != null) ? loggedIn.getUsername() : null;
        this.roles = (loggedIn != null && loggedIn.getAuthorities() != null) ? loggedIn.getAuthorities() : null;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
