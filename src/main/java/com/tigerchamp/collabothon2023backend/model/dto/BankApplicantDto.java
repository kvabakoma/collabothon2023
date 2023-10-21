package com.tigerchamp.collabothon2023backend.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tigerchamp.collabothon2023backend.model.entity.Bank;
import com.tigerchamp.collabothon2023backend.model.entity.Gender;
import org.bson.types.ObjectId;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BankApplicantDto {
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("user_id")
    private String name;
    @JsonProperty("user_id")
    private String family;
    @JsonProperty("user_id")
    private Gender gender;
    @JsonProperty("user_id")
    private String originCountry;
    @JsonProperty("user_id")
    private String currentCountry;
    @JsonProperty("user_id")
    private ObjectId documentID;
    @JsonProperty("user_id")
    private ObjectId documentRent;
    @JsonProperty("user_id")
    private ObjectId documentEmployment;
    @JsonProperty("user_id")
    private List<ObjectId> additionalDocuments;
    @JsonProperty("user_id")
    private List<Bank> banksApplied;

    public BankApplicantDto() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public String getCurrentCountry() {
        return currentCountry;
    }

    public void setCurrentCountry(String currentCountry) {
        this.currentCountry = currentCountry;
    }

    public ObjectId getDocumentID() {
        return documentID;
    }

    public void setDocumentID(ObjectId documentID) {
        this.documentID = documentID;
    }

    public ObjectId getDocumentRent() {
        return documentRent;
    }

    public void setDocumentRent(ObjectId documentRent) {
        this.documentRent = documentRent;
    }

    public ObjectId getDocumentEmployment() {
        return documentEmployment;
    }

    public void setDocumentEmployment(ObjectId documentEmployment) {
        this.documentEmployment = documentEmployment;
    }

    public List<ObjectId> getAdditionalDocuments() {
        return additionalDocuments;
    }

    public void setAdditionalDocuments(List<ObjectId> additionalDocuments) {
        this.additionalDocuments = additionalDocuments;
    }

    public List<Bank> getBanksApplied() {
        return banksApplied;
    }

    public void setBanksApplied(List<Bank> banksApplied) {
        this.banksApplied = banksApplied;
    }
}
