package com.tigerchamp.collabothon2023backend.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BankOverviewDto {
    @JsonProperty("total_number")
    private int totalNumber;
    @JsonProperty("banks")
    private List<BankDto> banks;

    public BankOverviewDto() {
    }

    public BankOverviewDto(int totalNumber, List<BankDto> banks) {
        this.totalNumber = totalNumber;
        this.banks = banks;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public List<BankDto> getBanks() {
        return banks;
    }
}
