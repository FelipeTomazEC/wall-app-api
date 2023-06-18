package com.github.felipetomazec.controllers;

import com.github.felipetomazec.dtos.AuthenticationRequest;
import com.github.felipetomazec.dtos.AuthenticationResponse;
import com.github.felipetomazec.dtos.SignUpHttpRequest;
import com.github.felipetomazec.services.CredentialsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthController {

    private final CredentialsService service;

    @PostMapping("/authors")
    public ResponseEntity<AuthenticationResponse> signUp(@Valid @RequestBody SignUpHttpRequest request) {
        var response = service.register(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/login")
    public AuthenticationResponse signIn(@Valid @RequestBody AuthenticationRequest request) {
        return service.signIn(request);
    }
}
