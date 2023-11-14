package com.example.midterneconermic.controller;


import com.example.midterneconermic.dto.*;
import com.example.midterneconermic.model.*;
import com.example.midterneconermic.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody AccountRequest accountRequest) {
        try {
            Account acc = authenticationService.signUp(accountRequest);
            return ResponseEntity.ok(acc);
        } catch (ResponseStatusException e) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage(e.getReason());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PostMapping("/sign-in")
    public ResponseEntity<JWTAuthenticationResponse> signIn(@RequestBody Account account) {
        JWTAuthenticationResponse jwtAuthenticationResponse = authenticationService.signIn(account);
        return ResponseEntity.ok(jwtAuthenticationResponse);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<JWTAuthenticationResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }




}
