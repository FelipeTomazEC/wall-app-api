package com.github.felipetomazec.repositories.entities;

import com.github.felipetomazec.entities.Post;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.ZoneOffset;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "posts")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostJpaEntity {
    @Id
    private String id;
    private String content;
    private String authorId;
    private Long createdAt;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<ReactionJpaEntity> reactions;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<CommentJpaEntity> comments;

    public static PostJpaEntity from(Post post) {
        var comments = post.getComments()
                .stream()
                .map(CommentJpaEntity::from)
                .collect(Collectors.toSet());

        var reactions = post.getReactions()
                .stream()
                .map((reaction) -> ReactionJpaEntity.from(post.getId(),  reaction))
                .collect(Collectors.toSet());

        return PostJpaEntity.builder()
                .authorId(post.getAuthorId())
                .id(post.getId())
                .content(post.getContent())
                .createdAt(post.getCreatedAt().toEpochSecond(ZoneOffset.UTC))
                .reactions(reactions)
                .comments(comments)
                .build();
    }
}
