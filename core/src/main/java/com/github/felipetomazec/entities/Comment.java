package com.github.felipetomazec.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
@Setter
public class Comment {
    private final String id;
    private String content;
    private final String authorId;
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
                    content,
                    authorId,
                    createdAt,
                    reactions
            );
        }
    }
}
