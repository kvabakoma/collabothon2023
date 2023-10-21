package com.tigerchamp.collabothon2023backend.configuration;

import io.jsonwebtoken.*;
import com.tigerchamp.collabothon2023backend.model.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    @Value("${chonov.app.jwtSecret}")
    private  String jwtSecret;
    @Value("${chonov.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateToken(Authentication authentication){

        String username = authentication.getName();

        return Jwts.builder().
                setSubject(username).
                setIssuedAt(new Date()).
                setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)).
                signWith(SignatureAlgorithm.HS512, jwtSecret).
                compact();
    }

    public String getUsernameFromJwtToken(String token){
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }
}
