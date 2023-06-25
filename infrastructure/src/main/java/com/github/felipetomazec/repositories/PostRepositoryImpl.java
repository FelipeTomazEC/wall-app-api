package com.github.felipetomazec.repositories;

import com.github.felipetomazec.entities.Post;
import com.github.felipetomazec.repositories.entities.CommentJpaEntity;
import com.github.felipetomazec.repositories.entities.PostJpaEntity;
import com.github.felipetomazec.repositories.entities.ReactionJpaEntity;
import com.github.felipetomazec.repositories.interfaces.PostJPARepository;
import com.github.felipetomazec.usecases.createpost.dependencies.CreatePostRepository;
import com.github.felipetomazec.usecases.retrieveallposts.dependencies.GetAllPostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements CreatePostRepository, GetAllPostsRepository {

    private final PostJPARepository repository;

    @Override
    public void create(Post post) {
        repository.save(PostJpaEntity.from(post));
    }

    @Override
    public List<Post> getAll() {
        return repository.findAll()
                .stream()
                .map(record -> {
                    var comments = record.getComments()
                            .stream()
                            .map(CommentJpaEntity::to)
                            .collect(Collectors.toSet());

                    var reactions = record.getReactions()
                            .stream()
                            .map(ReactionJpaEntity::to)
                            .collect(Collectors.toSet());

                    var createdAt = Instant.ofEpochSecond(record.getCreatedAt())
                            .atZone(ZoneOffset.UTC)
                            .toLocalDateTime();

                    return Post.builder()
                            .id(record.getId())
                            .authorId(record.getAuthorId())
                            .comments(comments)
                            .reactions(reactions)
                            .createdAt(createdAt)
                            .content(record.getContent())
                            .build();
                })
                .collect(Collectors.toList());
    }
}
