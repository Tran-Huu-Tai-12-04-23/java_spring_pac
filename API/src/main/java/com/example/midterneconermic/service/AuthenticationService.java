package com.example.midterneconermic.service;

import com.example.midterneconermic.dto.AccountRequest;
import com.example.midterneconermic.dto.JWTAuthenticationResponse;
import com.example.midterneconermic.dto.RefreshTokenRequest;
import com.example.midterneconermic.model.Account;

public interface AuthenticationService {
    Account signUp(AccountRequest account);

    JWTAuthenticationResponse signIn(Account account);

    JWTAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

    Account getAccount(String username);
}
