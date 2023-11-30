package com.example.midterneconermic.controller;


import com.example.midterneconermic.model.Account;
import com.example.midterneconermic.service.impl.AccountServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/common/account")
@RequiredArgsConstructor
public class UserAccountController {

    private final AccountServiceImpl accountServiceImpl;

    @GetMapping
    public ResponseEntity<?> getAccount(@RequestParam String username) {
        Optional<Account> account = accountServiceImpl.getAccountByUsername(username);
        if( account.isPresent()) {
            return  ResponseEntity.ok(account.get());
        }else {
            return  ResponseEntity.status(401).body("Account not found");
        }
    }

}
