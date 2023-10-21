package com.tigerchamp.collabothon2023backend.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

@Document(collection = "Role")
public class Role implements GrantedAuthority {
    private String id;
    private Authority authority;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority.toString();
    }
}
