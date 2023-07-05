package com.github.felipetomazec.repositories.entities;

import com.github.felipetomazec.entities.Reaction;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reactions")
public class ReactionJpaEntity {
    @EmbeddedId
    private ReactionId id;
    private String type;

    public static ReactionJpaEntity from(String referenceId, Reaction reaction) {
        var id = new ReactionId(reaction.getAuthorId().toString(), referenceId);

        return ReactionJpaEntity.builder()
                .id(id)
                .type(reaction.getType().name())
                .build();
    }

    public Reaction to() {
        return Reaction.builder()
                .authorId(UUID.fromString(id.authorId))
                .type(Reaction.Type.valueOf(type))
                .build();
    }

    @Embeddable
    @AllArgsConstructor
    private static class ReactionId implements Serializable {
        private String authorId;
        private String referenceId;
    }
}
