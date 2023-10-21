package com.tigerchamp.collabothon2023backend.init;

import com.tigerchamp.collabothon2023backend.service.RoleService;
import com.tigerchamp.collabothon2023backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("!test")
@Component
public class DataInit implements CommandLineRunner {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public DataInit(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.roleService.rolesInit();
        this.userService.usersInit();
    }
}
