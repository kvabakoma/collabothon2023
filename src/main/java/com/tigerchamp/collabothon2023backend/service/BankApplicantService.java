package com.tigerchamp.collabothon2023backend.service;

import com.tigerchamp.collabothon2023backend.model.dto.BankApplicantDto;
import com.tigerchamp.collabothon2023backend.model.dto.BankDto;
import com.tigerchamp.collabothon2023backend.model.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface BankApplicantService {
    BankApplicantDto getBankApplicantByUser(User user);
    BankApplicantDto addBankApplicant(BankApplicantDto bankApplicantDto);
    BankApplicantDto addBankToBankApplicant(User user, BankDto bank);
}
