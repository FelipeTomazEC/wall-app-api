package com.github.felipetomazec.dtos;

public record SignUpHttpRequest(
        String email,
        String password,
        String profileImageBase64,
        String name
) { }
