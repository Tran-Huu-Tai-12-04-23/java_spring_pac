package com.example.midterneconermic.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class AccountRequest {
    private String username;
    private String password;
    private String confirmPassword;
}
