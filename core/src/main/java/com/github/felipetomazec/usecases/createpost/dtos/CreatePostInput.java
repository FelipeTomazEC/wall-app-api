package com.github.felipetomazec.usecases.createpost.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CreatePostInput {
    private final UUID authorId;
    private final String content;
}
