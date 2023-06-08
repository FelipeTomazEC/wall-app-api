package com.github.felipetomazec.usecases.createpost.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreatePostOutput {
    private final String postId;
}
