package com.tigerchamp.collabothon2023backend.web;

import com.mongodb.lang.NonNull;
import com.tigerchamp.collabothon2023backend.model.dto.BankApplicantDto;
import com.tigerchamp.collabothon2023backend.model.dto.BankDto;
import com.tigerchamp.collabothon2023backend.model.entity.User;
import com.tigerchamp.collabothon2023backend.service.BankApplicantService;
import com.tigerchamp.collabothon2023backend.service.MongoGridFsService;
import com.tigerchamp.collabothon2023backend.exception.*;
import com.tigerchamp.collabothon2023backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("bank-applicant")
public class BankApplicantController {
    private final MongoGridFsService mongoGridFsService;
    private final BankApplicantService bankApplicantService;
    private final UserService userService;

    @Autowired
    public BankApplicantController(MongoGridFsService mongoGridFsService, BankApplicantService bankApplicantService, UserService userService) {
        this.mongoGridFsService = mongoGridFsService;
        this.bankApplicantService = bankApplicantService;
        this.userService = userService;
    }

    @PostMapping("/document/store")
    public String storeDocument(@RequestPart("document") MultipartFile document) {
        try {
            return mongoGridFsService.
                    storeDocumentAsImage(document.getInputStream(),
                                         document.getOriginalFilename()).toString();
        } catch (IOException e) {
            throw new InvalidFileException(e.getMessage());
        }
    }

    @GetMapping("/document/{id}")
    public File getDocumentById(@PathVariable("id") String id) {
        try {
            return mongoGridFsService.getFileById(id);
        } catch (IOException e) {
            throw new InvalidFileException(e.getMessage());
        }
    }

    @PostMapping("/create")
    public String createNewBankApplicant(@RequestBody @NonNull BankApplicantDto bankApplicantDto) {
       return bankApplicantService.addBankApplicant(bankApplicantDto).toString();
    }

    @PutMapping("/add-bank/{id}")
    public String addBankToBankApplicant(@PathVariable("id") String id, @RequestBody BankDto bankDto) {
        User user = userService.findById(id);
        if (user == null) {
            throw new UsernameNotFoundException("User does not exist!");
        }
       return bankApplicantService.addBankToBankApplicant(user, bankDto).toString();
    }
}
