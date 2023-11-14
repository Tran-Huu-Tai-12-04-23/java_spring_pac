package com.example.midterneconermic.service;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.Objects;

public interface JWTService {
    String generateToken(UserDetails userDetails);
    String generateRefreshToken(Map<String, Objects> extractClaims, UserDetails userDetails);

    String extractUsername(String token) ;

    boolean isTokenValid(String token, UserDetails userDetails);


}
