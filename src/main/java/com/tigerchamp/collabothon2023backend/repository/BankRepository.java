package com.tigerchamp.collabothon2023backend.repository;

import com.tigerchamp.collabothon2023backend.model.entity.Bank;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankRepository extends MongoRepository<Bank, String> {
    List<Bank> findAll();
    Bank findByName(String name);
}
