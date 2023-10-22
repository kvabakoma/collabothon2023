package com.tigerchamp.collabothon2023backend.model.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("bank_applicant")
public class BankApplicant {
    private String id;
    private String userId;
    private String name;
    private String family;
    private Gender gender;
    private String originCountry;
    private String currentCountry;
    private String documentID;
    private String documentRent;
    private String documentEmployment;
    private List<String> additionalDocuments;
    private List<Bank> banksApplied;

    public BankApplicant() {
    }

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public void addBank(Bank bank) {
        if (banksApplied != null) {
            banksApplied.add(bank);
        }
    }
}
