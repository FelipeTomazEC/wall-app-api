package com.github.felipetomazec.usecases.createpost.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreatePostInput {
    private final String authorId;
    private final String content;
}
