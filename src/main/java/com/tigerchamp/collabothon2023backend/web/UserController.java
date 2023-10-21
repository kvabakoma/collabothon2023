package com.tigerchamp.collabothon2023backend.web;

import com.mongodb.lang.NonNull;
import com.tigerchamp.collabothon2023backend.configuration.JwtUtils;
import com.tigerchamp.collabothon2023backend.model.binding.ErrorMessage;
import com.tigerchamp.collabothon2023backend.model.dto.AuthenticatedUserDto;
import com.tigerchamp.collabothon2023backend.model.dto.UserLoginDto;
import com.tigerchamp.collabothon2023backend.model.entity.User;
import com.tigerchamp.collabothon2023backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtils jwtUtils;

    @Autowired
    public UserController(AuthenticationManager authenticationManager, UserService userService, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public AuthenticatedUserDto authenticateUser(@RequestBody @NonNull UserLoginDto userLogin){
        User loadUser = (User) this.userService.loadUserByUsername(userLogin.getUsername());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLogin.getUsername(),
                        userLogin.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateToken(authentication);
        return new AuthenticatedUserDto(loadUser, jwt);
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null){
            SecurityContextHolder.getContext().setAuthentication(null);
            return ResponseEntity.ok("Logout Successful!!!");
        }
        return ResponseEntity.ok("Please login");
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> message(){
        return ResponseEntity.ok("User Content");
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorMessage> userNotFound(UsernameNotFoundException unf){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setFieldError("username");
        errorMessage.setErrorMessage(unf.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorMessage);
    }


    @GetMapping("/register")
    public String register(Model model){
//        if (!model.containsAttribute("userRegisterBindingModel")){
//            model.addAttribute("userRegisterBindingModel",new UserRegisterBindingModel());
//        }

        return "register";
    }
}
