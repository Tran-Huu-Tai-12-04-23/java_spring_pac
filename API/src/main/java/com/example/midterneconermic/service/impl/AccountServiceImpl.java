package com.example.midterneconermic.service.impl;

import com.example.midterneconermic.exception.*;
import com.example.midterneconermic.model.Account;
import com.example.midterneconermic.repository.AccountRepository;
import com.example.midterneconermic.service.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService implements IAccountService {
    private final AccountRepository accountRepository;

    public Account update(Account account, Long id) {
        return accountRepository.findById(id).map( ac -> {
            ac.setUsername(account.getUsername());
            ac.setPassword(account.getPassword());
            ac.setRole(account.getRole());
            return accountRepository.save(ac);
        }).orElseThrow(() -> new NotFoundException("Sorry, ths acount could not be found"));
    }

    public List<Account> getAllAccount() {
        return accountRepository.findAll();
    }

    @Override
    public Account deleteSoftAccount(Long id) {
        if( !accountRepository.existsById(id)) {
            throw new NotFoundException("Sorry, student not found!");
        }

        return accountRepository.findById(id).map( ac -> {
            ac.setIsDelete(true);
            return accountRepository.save(ac);
        }).orElseThrow(() -> new NotFoundException("Sorry, ths acount could not be found"));
    }

    @Override
    public void deleteAccount(Long id) {
        if( !accountRepository.existsById(id)) {
            throw new NotFoundException("Sorry, account not found!");
        }

        accountRepository.deleteById(id);
    }


    public Account createNew(Account account) {
        if( accountAlreadyExist(account.getUsername()) ){
            throw new AlreadyExistException(account.getUsername() + " already exists!");
        }
        return accountRepository.save(account);
    }

    private boolean accountAlreadyExist(String username) {
        return accountRepository.findByUsername(username).isPresent();
    }

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                try {
                    return accountRepository.findByUsername(username).orElseThrow( () -> new AccountNotFoundException("Account not found"));
                } catch (AccountNotFoundException e) {
                    throw new RuntimeException(e);
                }

            }
        };
    }

    @Override
    public Optional<Account> getAccountByUsername(String username )
    {
        return accountRepository.findByUsername(username);
    }



}
