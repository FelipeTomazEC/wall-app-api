package com.github.felipetomazec.entities;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class Author {
    private UUID id;
    private String username;
    private String profileImage;
    private String email;
}
