package com.github.felipetomazec.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Builder
@Getter
@AllArgsConstructor
public class Post {
    private final UUID id;
    private final UUID authorId;
    private String content ;
    private final LocalDateTime createdAt;
    private final Set<Reaction> reactions;

    public Set<Reaction> getReactions() {
        return Collections.unmodifiableSet(reactions);
    }

    public void addReaction(Reaction reaction) {
        reactions.removeIf(r -> r.getAuthorId().equals(reaction.getAuthorId()));
        reactions.add(reaction);
    }

    public void removeReaction(Reaction reaction) {
        reactions.remove(reaction);
    }

    public static class PostBuilder {
        public Post build() {
            if(reactions == null) {
                reactions = new HashSet<>();
            }

            if(createdAt == null) {
                createdAt = LocalDateTime.now();
            }

            return new Post(
                    id,
                    authorId,
                    content,
                    createdAt,
                    reactions
            );
        }
    }
}
