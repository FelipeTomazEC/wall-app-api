package com.github.felipetomazec.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SignUpHttpRequest(
        @Email(message = "Field 'email' must be a valid e-mail address")
        String email,
        @NotBlank(message = "Field 'password' cannot be empty/null")
        String password,
        @NotBlank(message = "Field 'profileImageBase64' cannot be empty/null")
        String profileImageBase64,
        @NotBlank(message = "Field 'name' cannot be empty/null")
        String name
) { }
