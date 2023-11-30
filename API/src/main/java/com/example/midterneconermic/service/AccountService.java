package com.example.midterneconermic.service;

import com.example.midterneconermic.model.Account;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface IAccountService {
    Account createNew(Account ac);
    Account update(Account ac, Long id);
    List<Account> getAllAccount();
    Account deleteSoftAccount(Long id);
    void deleteAccount(Long id);

    UserDetailsService userDetailsService();

    Optional<Account> getAccountByUsername(String username);


}
