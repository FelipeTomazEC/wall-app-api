package com.github.felipetomazec.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Reaction {
    private final String authorId;
    private final Type type;

    public enum Type {
        DISLIKE,
        LIKE,
        LOVE
    }
}
