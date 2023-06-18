package com.github.felipetomazec.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthenticationRequest(
        @Email(message = "Field 'email' must be a valid e-mail address")
        String email,
        @NotBlank(message = "Field 'password' cannot be empty/null ")
        String password
) { }
