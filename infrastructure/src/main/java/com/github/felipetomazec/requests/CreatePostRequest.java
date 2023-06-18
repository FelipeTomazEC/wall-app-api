package com.github.felipetomazec.requests;

import jakarta.validation.constraints.NotBlank;

public record CreatePostRequest(
    @NotBlank(message = "Param 'content' cannot be empty.")
     String content
) {}
