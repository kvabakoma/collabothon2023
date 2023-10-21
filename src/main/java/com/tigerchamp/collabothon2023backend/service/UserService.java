package com.tigerchamp.collabothon2023backend.service;

import com.tigerchamp.collabothon2023backend.model.dto.UserRegisterDto;
import com.tigerchamp.collabothon2023backend.model.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface UserService extends UserDetailsService {
    User register(UserRegisterDto registerDto);

    User findByUsername(String username);

    User findById(String id);

    Set<User> findAllEnabledFalseUsers();

    User editUserProfile(User user ,String oldPassword);

    List<User> findAll();

    void userSetRole(String username,String authority);

    void changeUserStatus(String username, String status);

    void usersInit();

    void deleteAllNotEnabledUsers();

    void deleteUserByUsername(String username);
}
