package com.github.felipetomazec.entities;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Author {
    private String id;
    private String username;
    private String profileImage;
    private String email;
}
