package com.tigerchamp.collabothon2023backend.service.impl;

import com.tigerchamp.collabothon2023backend.model.dto.BankDto;
import com.tigerchamp.collabothon2023backend.model.dto.BankOverviewDto;
import com.tigerchamp.collabothon2023backend.model.entity.Bank;
import com.tigerchamp.collabothon2023backend.repository.BankRepository;
import com.tigerchamp.collabothon2023backend.service.BankService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BankServiceImpl implements BankService {
    private final BankRepository bankRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public BankServiceImpl(BankRepository bankRepository, ModelMapper modelMapper) {
        this.bankRepository = bankRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Bank addBank(BankDto dto) {
        Bank bank = modelMapper.map(dto, Bank.class);
        if (bank != null) {
            bankRepository.save(bank);
        }
        return bank;
    }

    @Override
    public BankDto getBanksByName(String name) {
        Bank bank = bankRepository.findByName(name);
        if (bank != null) {
            return modelMapper.map(bank, BankDto.class);
        }
        return null;
    }

    @Override
    public BankOverviewDto getAllBanks() {
        List<Bank> bankList = bankRepository.findAll();
        if (bankList != null && !bankList.isEmpty()) {
            List<BankDto> bankDtos = bankList.stream()
                    .map(bank -> modelMapper.map(bank, BankDto.class))
                    .toList();
            return new BankOverviewDto(bankDtos.size(), bankDtos);
        }
        return null;
    }

    @Override
    public void bankInit() {
        if (this.bankRepository.count() == 0) {
            Map<String, Boolean> bankRequirements = new HashMap<>();
                    bankRequirements.put("documentID", Boolean.TRUE);
                    bankRequirements.put("documentRent", Boolean.TRUE);
                    bankRequirements.put("documentEmployment", Boolean.TRUE);
            Map<String, Boolean> additionalDocuments = Map.of(
                    "documentOther1", Boolean.FALSE,
                    "documentOther2", Boolean.FALSE
            );
            // 1
            Bank bankUbb = new Bank();
            bankUbb.setName("UBB");
            bankUbb.setDescription("It is the first and largest banking consolidation project in Bulgaria");
            bankUbb.setRequirements(bankRequirements);
            // 2
            Bank bankDSK = new Bank();
            bankDSK.setName("DSK");
            bankDSK.setDescription("DSK Bank is a major Bulgarian bank. It has been owned by the Hungarian OTP Bank since 2003.");
            bankDSK.setRequirements(bankRequirements);
            // 3
            Bank postBank = new Bank();
            postBank.setName("Postbank");
            postBank.setDescription("Postbank, legally named Eurobank Bulgaria AD, is the fifth biggest bank in Bulgaria in terms of assets, having a broad branch network across the country");
            postBank.setRequirements(bankRequirements);
            // 4
            bankRequirements.putAll(additionalDocuments);
            Bank bankFIB = new Bank();
            bankFIB.setName("FIBank");
            bankFIB.setDescription("First Investment Bank or Fibank, founded on 8 October 1993, is currently the third largest Bulgarian bank. ");
            bankFIB.setRequirements(bankRequirements);
            // 5
            Bank proCredit = new Bank();
            proCredit.setName("ProCredit Bank");
            proCredit.setDescription("The ProCredit Holding is the parent company of a development-oriented group of commercial banks for small and medium enterprises, which operate in Southeastern Europe, Eastern Europe, Ecuador, and Germany. ");
            proCredit.setRequirements(bankRequirements);
            bankRepository.save(bankUbb);
            bankRepository.save(bankDSK);
            bankRepository.save(postBank);
            bankRepository.save(bankFIB);
            bankRepository.save(proCredit);
        }
    }
}
