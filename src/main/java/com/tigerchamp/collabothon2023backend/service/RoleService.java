package com.tigerchamp.collabothon2023backend.service;

import com.tigerchamp.collabothon2023backend.model.entity.Authority;
import com.tigerchamp.collabothon2023backend.model.entity.Role;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface RoleService {
    Role findByAuthority(Authority authority);

    void addRole(Authority authority);

    void rolesInit();

    Set<Role> findAll();
}
