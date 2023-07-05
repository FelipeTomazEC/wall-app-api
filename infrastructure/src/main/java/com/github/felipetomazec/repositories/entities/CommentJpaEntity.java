package com.github.felipetomazec.repositories.entities;

import com.github.felipetomazec.entities.Comment;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.ZoneOffset;
import java.util.Set;
import java.util.UUID;
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

    @ManyToOne
    private PostJpaEntity post;

    @OneToMany
    private Set<ReactionJpaEntity> reactions;

    public static CommentJpaEntity from(Comment comment) {
        var reactions = comment.getReactions()
                .stream()
                .map((reaction) -> ReactionJpaEntity.from(comment.getId().toString(), reaction))
                .collect(Collectors.toSet());

        var post = PostJpaEntity.builder()
                .id(comment.getPostId().toString())
                .build();

        return CommentJpaEntity.builder()
                .authorId(comment.getAuthorId().toString())
                .id(comment.getId().toString())
                .post(post)
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
                .id(UUID.fromString(id))
                .postId(UUID.fromString(post.getId()))
                .content(content)
                .authorId(UUID.fromString(authorId))
                .reactions(reactions)
                .build();
    }
}
