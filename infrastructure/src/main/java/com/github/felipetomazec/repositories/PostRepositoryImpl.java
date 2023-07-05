package com.github.felipetomazec.repositories;

import com.github.felipetomazec.entities.Post;
import com.github.felipetomazec.repositories.entities.PostJpaEntity;
import com.github.felipetomazec.repositories.entities.ReactionJpaEntity;
import com.github.felipetomazec.repositories.interfaces.PostJPARepository;
import com.github.felipetomazec.usecases.createpost.dependencies.CreatePostRepository;
import com.github.felipetomazec.usecases.retrieveallposts.dependencies.GetAllPostsRepository;
import com.github.felipetomazec.usecases.retrieveallposts.dependencies.GetCommentsCountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class  PostRepositoryImpl implements CreatePostRepository, GetAllPostsRepository, GetCommentsCountRepository {

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
                    var reactions = record.getReactions()
                            .stream()
                            .map(ReactionJpaEntity::to)
                            .collect(Collectors.toSet());

                    var createdAt = Instant.ofEpochSecond(record.getCreatedAt())
                            .atZone(ZoneOffset.UTC)
                            .toLocalDateTime();

                    return Post.builder()
                            .id(UUID.fromString(record.getId()))
                            .authorId(UUID.fromString(record.getAuthorId()))
                            .reactions(reactions)
                            .createdAt(createdAt)
                            .content(record.getContent())
                            .build();
                })
                .collect(Collectors.toList());
    }

    @Override
    public Map<Post, Long> getCommentsCount(List<Post> posts) {
        var postMap = posts
                .stream()
                .collect(Collectors.toMap(post -> post.getId().toString(), post -> post));

        var count = repository.countCommentsByPostIds(postMap.keySet().stream().toList());

        Function<Map<String, Object>, Post> keyMapper = (value) -> postMap.get((String) value.get("postId"));
        Function<Map<String, Object>, Long> valueMapper = (value) -> (Long) value.get("commentCount");

        return count
                .parallelStream()
                .collect(Collectors.toMap(keyMapper, valueMapper));
    }
}
