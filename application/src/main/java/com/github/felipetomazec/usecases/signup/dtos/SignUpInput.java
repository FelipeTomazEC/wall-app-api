package com.github.felipetomazec.usecases.signup.dtos;

public record SignUpInput (
        String username,
        String email,
        String profileImage
) { }
