package com.example.midterneconermic.controller;


import com.example.midterneconermic.dto.AccountRequest;
import com.example.midterneconermic.dto.ErrorResponse;
import com.example.midterneconermic.dto.JWTAuthenticationResponse;
import com.example.midterneconermic.dto.RefreshTokenRequest;
import com.example.midterneconermic.model.Account;
import com.example.midterneconermic.model.Product;
import com.example.midterneconermic.service.AuthenticationService;
import com.example.midterneconermic.service.ProductService;
import com.example.midterneconermic.service.impl.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/app/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/get-account")
    public ResponseEntity<?> getAccount(@RequestParam Account acc) {
        System.out.println("hello");
        System.out.println(acc.getUsername());
        Optional<Account> account = accountService.getAccountByUsername(acc.getUsername());

        if( account.isPresent()) {
            return  ResponseEntity.ok(account.get());
        }else {
            return  ResponseEntity.badRequest().body("Account not found");
        }
    }

}
