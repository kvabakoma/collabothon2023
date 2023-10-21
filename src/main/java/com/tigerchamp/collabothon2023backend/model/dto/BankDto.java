package com.tigerchamp.collabothon2023backend.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class BankDto {
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("requirements")
    private Map<String, String> requirements;

    public BankDto() {
    }

    public BankDto(String name, String description, Map<String, String> requirements) {
        this.name = name;
        this.description = description;
        this.requirements = requirements;
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

    public Map<String, String> getRequirements() {
        return requirements;
    }

    public void setRequirements(Map<String, String> requirements) {
        this.requirements = requirements;
    }
}
