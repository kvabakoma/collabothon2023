package com.tigerchamp.collabothon2023backend.service.impl;

import com.tigerchamp.collabothon2023backend.model.dto.UserRegisterDto;
import com.tigerchamp.collabothon2023backend.model.entity.Authority;
import com.tigerchamp.collabothon2023backend.model.entity.Role;
import com.tigerchamp.collabothon2023backend.model.entity.User;
import com.tigerchamp.collabothon2023backend.repository.UserRepository;
import com.tigerchamp.collabothon2023backend.service.RoleService;
import com.tigerchamp.collabothon2023backend.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleService roleService, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found!!!"));
    }

    @Override
    public User register(UserRegisterDto registerDto) {
        User userToRegister = modelMapper.map(registerDto,User.class);
        userToRegister.setEnabled(true);
        userToRegister.setCredentialsNonExpired(true);
        userToRegister.setAccountNonLocked(true);
        userToRegister.setAccountNonExpired(true);
        Set<Role> userRole = new HashSet<>();
        userRole.add(this.roleService.findByAuthority(Authority.ROLE_USER));
        userToRegister.setAuthorities(userRole);
        if(userToRegister.getPassword() != null){
            userToRegister.setPassword(passwordEncoder.encode(userToRegister.getPassword()));
        }
        this.userRepository.save(userToRegister);

        return userToRegister;
    }

    @Override
    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public User findById(String id) {
        return this.userRepository.findById(id).orElse(null);
    }

    @Override
    public Set<User> findAllEnabledFalseUsers() {
        return this.userRepository.findAllByEnabledFalse();
    }

    @Override
    public User editUserProfile(User user, String oldPassword) {

        User newPasswordUser = this.userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Username not found!!!"));
        if (!passwordEncoder.matches(oldPassword,user.getPassword())){
            throw new IllegalArgumentException("Incorrect Password!");
        }
        newPasswordUser.setPassword(user.getPassword() != null ? this.passwordEncoder.encode(user.getPassword()) : user.getPassword());
        this.userRepository.save(newPasswordUser);
        return newPasswordUser;
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll().stream()
                .filter(user -> user.getAuthorities().size() < 6).toList();
    }

    @Override
    public void userSetRole(String username, String authority) {
        User usm = this.findByUsername(username);
        usm.getAuthorities().clear();
        switch (authority) {
            case "ROLE_USER" -> usm.getAuthorities().add(this.roleService.findByAuthority(Authority.ROLE_USER));
            case "ROLE_ADMIN" -> {
                usm.getAuthorities().add(this.roleService.findByAuthority(Authority.ROLE_ADMIN));
            }
        }
        this.userRepository.save(usm);
    }

    @Override
    public void changeUserStatus(String username, String status) {
        User usm = this.findByUsername(username);
        switch (status) {
            case "ENABLED" -> {
                usm.setEnabled(true);
                usm.setAccountNonExpired(true);
                usm.setAccountNonLocked(true);
                usm.setCredentialsNonExpired(true);
            }
            case "DISABLED" -> usm.setEnabled(false);
        }
        this.userRepository.save(usm);
    }

    @Override
    public void usersInit() {
        if (this.userRepository.count() == 0){
            User root = new User();
            root.setUsername("Superuser");
            root.setPassword(this.passwordEncoder.encode("1234"));
            Set<Role> roles = this.roleService.findAll();
            root.setEnabled(true);
            root.setAuthorities(roles);
            root.setAccountNonExpired(true);
            root.setAccountNonLocked(true);
            root.setCredentialsNonExpired(true);
            root.setFirstName("Chono");
            root.setLastName("Chonov");


            User user = new User();
            user.setUsername("pesho");
            user.setPassword(this.passwordEncoder.encode("pesho2"));
            Set<Role> userRole = new HashSet<>();
            userRole.add(this.roleService.findByAuthority(Authority.ROLE_USER));
            user.setAuthorities(userRole);
            user.setEnabled(true);
            user.setAccountNonExpired(true);
            user.setAccountNonLocked(true);
            user.setCredentialsNonExpired(true);
            user.setFirstName("Pesho");
            user.setLastName("Radinov");

            this.userRepository.save(root);

            this.userRepository.save(user);

        }
    }

    @Transactional
    @Override
    public void deleteAllNotEnabledUsers() {
        if (this.findAllEnabledFalseUsers() != null){
            Set<User> notEnabled = this.findAllEnabledFalseUsers();
            for (User u: notEnabled) {
                this.deleteUserByUsername(u.getUsername());
            }
        }
    }

    @Transactional
    @Override
    public void deleteUserByUsername(String username) {
        if (this.findByUsername(username) != null){
            this.userRepository.deleteByUsername(username);
        }
    }
}
