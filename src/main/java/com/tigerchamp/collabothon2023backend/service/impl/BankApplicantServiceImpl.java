package com.tigerchamp.collabothon2023backend.service.impl;

import com.tigerchamp.collabothon2023backend.model.dto.BankApplicantDto;
import com.tigerchamp.collabothon2023backend.model.dto.BankDto;
import com.tigerchamp.collabothon2023backend.model.entity.Bank;
import com.tigerchamp.collabothon2023backend.model.entity.BankApplicant;
import com.tigerchamp.collabothon2023backend.model.entity.User;
import com.tigerchamp.collabothon2023backend.repository.BankApplicantRepository;
import com.tigerchamp.collabothon2023backend.repository.BankRepository;
import com.tigerchamp.collabothon2023backend.service.BankApplicantService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class BankApplicantServiceImpl implements BankApplicantService {
    private final BankRepository bankRepository;
    private final BankApplicantRepository bankApplicantRepository;
    private final ModelMapper modelMapper;

    public BankApplicantServiceImpl(BankRepository bankRepository, BankApplicantRepository bankApplicantRepository, ModelMapper modelMapper) {
        this.bankRepository = bankRepository;
        this.bankApplicantRepository = bankApplicantRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public BankApplicantDto getBankApplicantByUser(User user) {
        BankApplicant bankApplicant = bankApplicantRepository.findByUserId(user.getId());
        return (bankApplicant != null) ? modelMapper.map(bankApplicant,BankApplicantDto.class) : null;
    }

    @Override
    public BankApplicantDto addBankApplicant(BankApplicantDto bankApplicantDto) {
        if (bankApplicantDto != null) {
            BankApplicant bankApplicant = modelMapper.map(bankApplicantDto, BankApplicant.class);
            bankApplicantRepository.save(bankApplicant);
        }
        return bankApplicantDto;
    }

    @Override
    public BankApplicantDto addBankToBankApplicant(User user, BankDto bank) {
        if (user != null && bank != null) {
            BankApplicant bankApplicant = bankApplicantRepository.findByUserId(user.getId());
            Bank fromDb = bankRepository.findByName(bank.getName());
            if (fromDb != null) {
                bankApplicant.addBank(fromDb);
                bankApplicantRepository.save(bankApplicant);
            }
            return modelMapper.map(bankApplicant, BankApplicantDto.class);
        }
        return null;
    }
}
