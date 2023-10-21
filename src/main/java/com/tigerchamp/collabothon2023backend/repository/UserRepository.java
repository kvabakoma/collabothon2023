package com.tigerchamp.collabothon2023backend.repository;

import com.tigerchamp.collabothon2023backend.model.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);

    @Query(value = "{'username',$0}",delete = true)
    void deleteByUsername(String username);

    Set<User> findAllByEnabledFalse();
}
