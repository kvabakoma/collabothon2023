package com.tigerchamp.collabothon2023backend.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "Bank")
public class Bank {
    private String id;
    private String name;
    private String description;
    private Map<String, Boolean> requirements;

    public Bank() {
    }

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, Boolean> getRequirements() {
        return requirements;
    }

    public void setRequirements(Map<String, Boolean> requirements) {
        this.requirements = requirements;
    }
}
