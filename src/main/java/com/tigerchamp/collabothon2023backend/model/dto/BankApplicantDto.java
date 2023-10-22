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
    @JsonProperty("name")
    private String name;
    @JsonProperty("family_name")
    private String familyName;
    @JsonProperty("gender")
    private Gender gender;
    @JsonProperty("origin_country")
    private String originCountry;
    @JsonProperty("current_country")
    private String currentCountry;
    @JsonProperty("document_iD")
    private String documentID;
    @JsonProperty("document_rent")
    private String documentRent;
    @JsonProperty("document_employment")
    private String documentEmployment;
    @JsonProperty("additional_documents")
    private List<String> additionalDocuments;
    @JsonProperty("banks_applied")
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

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
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

    public String getDocumentID() {
        return documentID;
    }

    public void setDocumentID(String documentID) {
        this.documentID = documentID;
    }

    public String getDocumentRent() {
        return documentRent;
    }

    public void setDocumentRent(String documentRent) {
        this.documentRent = documentRent;
    }

    public String getDocumentEmployment() {
        return documentEmployment;
    }

    public void setDocumentEmployment(String documentEmployment) {
        this.documentEmployment = documentEmployment;
    }

    public List<String> getAdditionalDocuments() {
        return additionalDocuments;
    }

    public void setAdditionalDocuments(List<String> additionalDocuments) {
        this.additionalDocuments = additionalDocuments;
    }

    public List<Bank> getBanksApplied() {
        return banksApplied;
    }

    public void setBanksApplied(List<Bank> banksApplied) {
        this.banksApplied = banksApplied;
    }
}
