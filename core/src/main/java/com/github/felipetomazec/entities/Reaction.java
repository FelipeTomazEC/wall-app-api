package com.github.felipetomazec.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class Reaction {
    private final UUID authorId;
    private final Type type;

    public enum Type {
        DISLIKE,
        LIKE,
        LOVE
    }
}
