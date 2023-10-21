package com.tigerchamp.collabothon2023backend.service.impl;

import com.tigerchamp.collabothon2023backend.model.entity.Authority;
import com.tigerchamp.collabothon2023backend.model.entity.Role;
import com.tigerchamp.collabothon2023backend.repository.RoleRepository;
import com.tigerchamp.collabothon2023backend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByAuthority(Authority authority) {
        return this.roleRepository.findByAuthority(authority).orElse(null);
    }

    @Override
    public void addRole(Authority authority) {
        if (this.roleRepository.findByAuthority(authority).isEmpty()){
            Role role = new Role();
            role.setAuthority(authority);
            this.roleRepository.save(role);
        }
    }

    @Override
    public void rolesInit() {
        if (this.roleRepository.count() == 0){
            Role admin = new Role();
            admin.setAuthority(Authority.ROLE_ADMIN);
            Role user = new Role();
            user.setAuthority(Authority.ROLE_USER);

            this.roleRepository.save(admin);
            this.roleRepository.save(user);

        }
    }

    @Override
    public Set<Role> findAll() {
        return this.roleRepository.findAll().stream().collect(Collectors.toUnmodifiableSet());
    }
}
