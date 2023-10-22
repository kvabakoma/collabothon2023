package com.tigerchamp.collabothon2023backend.repository;

import com.tigerchamp.collabothon2023backend.model.entity.BankApplicant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankApplicantRepository extends MongoRepository<BankApplicant, String> {
    BankApplicant findByUserId(String userId);
    List<BankApplicant> findAll();
}
