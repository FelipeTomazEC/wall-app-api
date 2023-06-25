package com.github.felipetomazec.repositories.entities;

import com.github.felipetomazec.entities.Comment;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.ZoneOffset;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comments")
public class CommentJpaEntity {
    @Id
    private String id;
    private String content;
    private String authorId;
    private Long createdAt;

    @OneToMany
    private Set<ReactionJpaEntity> reactions;

    public static CommentJpaEntity from(Comment comment) {
        var reactions = comment.getReactions()
                .stream()
                .map((reaction) -> ReactionJpaEntity.from(comment.getId(), reaction))
                .collect(Collectors.toSet());

        return CommentJpaEntity.builder()
                .authorId(comment.getAuthorId())
                .id(comment.getId())
                .createdAt(comment.getCreatedAt().toEpochSecond(ZoneOffset.UTC))
                .content(comment.getContent())
                .reactions(reactions)
                .build();
    }

    public Comment to() {
        var reactions = this.reactions.stream()
                .map(ReactionJpaEntity::to)
                .collect(Collectors.toSet());

        return Comment.builder()
                .id(id)
                .content(content)
                .authorId(authorId)
                .reactions(reactions)
                .build();
    }
}
