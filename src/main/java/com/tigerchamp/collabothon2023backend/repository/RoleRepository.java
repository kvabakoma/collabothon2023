package com.tigerchamp.collabothon2023backend.repository;

import com.tigerchamp.collabothon2023backend.model.entity.Authority;
import com.tigerchamp.collabothon2023backend.model.entity.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByAuthority(Authority authority);
}
