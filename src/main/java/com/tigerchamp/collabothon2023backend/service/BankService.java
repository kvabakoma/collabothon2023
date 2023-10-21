package com.tigerchamp.collabothon2023backend.service;

import com.tigerchamp.collabothon2023backend.model.dto.BankDto;
import com.tigerchamp.collabothon2023backend.model.dto.BankOverviewDto;
import com.tigerchamp.collabothon2023backend.model.entity.Bank;
import org.springframework.stereotype.Service;

@Service
public interface BankService {
    Bank addBank(BankDto dto);
    BankDto getBanksByName(String name);
    BankOverviewDto getAllBanks();
    void bankInit();
}
