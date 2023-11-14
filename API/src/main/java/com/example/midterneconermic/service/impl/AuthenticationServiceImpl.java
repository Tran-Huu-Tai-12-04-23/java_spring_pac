package com.example.midterneconermic.service.impl;

import com.example.midterneconermic.dto.AccountRequest;
import com.example.midterneconermic.dto.JWTAuthenticationResponse;
import com.example.midterneconermic.dto.RefreshTokenRequest;
import com.example.midterneconermic.model.Account;
import com.example.midterneconermic.model.Role;
import com.example.midterneconermic.repository.AccountRepository;
import com.example.midterneconermic.service.JWTService;
import com.example.midterneconermic.dto.*;
import com.example.midterneconermic.model.*;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.midterneconermic.service.AuthenticationService;

import java.util.HashMap;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    private final String USER_EMPTY = "Please enter username";
    private final String PASSWORD_EMPTY = "Please enter password";
    private final String CONFIRM_PASSWORD_EMPTY = "Please enter confirm password";
    private final String EMAIL_EMPTY = "Please enter email";
    private final String PHONE_NUMBER_EMPTY = "Please enter phone number";
    private final String EMAIL_INVALID = "Email invalid";
    private final String PHONE_NUMBER_INVALID = "Phone number invalid invalid";
    private final String PASSWORD_CONFIRM_NOT_MATCH = "Password and confirm password does not match!";


    public Account signUp(AccountRequest account) throws ResponseStatusException{

        boolean checkAccountExist = accountRepository.existsByUsername(account.getUsername());

        if( checkAccountExist ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account already exists");
        }

        String checkDataInputMessage = checkRequestDataInput(account);

        if( !checkDataInputMessage.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, checkDataInputMessage);
        }

        Account newAccount = new Account();
        newAccount.setUsername(account.getUsername());
        newAccount.setRole(Role.USER);
        newAccount.setPassword(passwordEncoder.encode(account.getPassword()));
        newAccount.setIsDelete(false);
        return accountRepository.save(newAccount);
    }

    private String checkRequestDataInput(AccountRequest account) {

        if(account.getUsername() == null || account.getUsername().isEmpty() ) {
            return USER_EMPTY;
        }

        if(account.getPassword() == null|| account.getPassword().isEmpty()) return PASSWORD_EMPTY;

        if( account.getConfirmPassword() == null || account.getConfirmPassword().isEmpty()) return CONFIRM_PASSWORD_EMPTY;

        return "";
    }
    public JWTAuthenticationResponse signIn(Account account) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(account.getUsername(), account.getPassword()));

        var acc = accountRepository.findByUsername(account.getUsername()).orElseThrow(() -> new IllegalAccessError("Invalid user name or password"));
        var jwtToken = jwtService.generateToken(acc);
        var jwtRefreshToken = jwtService.generateRefreshToken(new HashMap<>() , acc);

        JWTAuthenticationResponse jwtAuthenticationResponse = new JWTAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwtToken);
        jwtAuthenticationResponse.setRefreshToken(jwtRefreshToken);

        return jwtAuthenticationResponse;
    }

    public JWTAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String username = jwtService.extractUsername(refreshTokenRequest.getToken());
        Account acc = accountRepository.findByUsername(username).orElseThrow();

        if( jwtService.isTokenValid(refreshTokenRequest.getToken(), acc)) {
            var jwtToken = jwtService.generateToken(acc);

            JWTAuthenticationResponse jwtAuthenticationResponse = new JWTAuthenticationResponse();
            jwtAuthenticationResponse.setToken(jwtToken);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());

            return jwtAuthenticationResponse;

        }

        return null;
    }

    @Override
    public Account getAccount(String username) {
        return  accountRepository.findByUsername(username).orElseThrow(() -> new IllegalAccessError("Account not found"));
    }


}
