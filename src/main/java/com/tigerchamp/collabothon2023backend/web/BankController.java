package com.tigerchamp.collabothon2023backend.web;

import com.tigerchamp.collabothon2023backend.model.dto.BankDto;
import com.tigerchamp.collabothon2023backend.model.dto.BankOverviewDto;
import com.tigerchamp.collabothon2023backend.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank")
public class BankController {

    private final BankService bankService;

    @Autowired
    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping("/bank")
    public BankOverviewDto getBanksList() {
       return bankService.getAllBanks();
    }

    @GetMapping("/bank/{name}")
    public BankDto getBanksList(@PathVariable("name") String name) {
        return bankService.getBanksByName(name);
    }
}
