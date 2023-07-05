package com.github.felipetomazec.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Builder
@Getter
@Setter
public class Comment {
    private final UUID id;
    private final UUID postId;
    private final UUID authorId;
    private String content;
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

    public static class CommentBuilder {
        public Comment build() {
            if(reactions == null) {
                reactions = new HashSet<>();
            }

            if(createdAt == null) {
                createdAt = LocalDateTime.now();
            }

            return new Comment(
                    id,
                    postId,
                    authorId,
                    content,
                    createdAt,
                    reactions
            );
        }
    }
}
