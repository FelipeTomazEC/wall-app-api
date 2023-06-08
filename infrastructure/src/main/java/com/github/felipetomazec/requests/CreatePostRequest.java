package com.github.felipetomazec.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreatePostRequest {
    @NotBlank(message = "Param 'content' cannot be empty.")
    private String content;
}
