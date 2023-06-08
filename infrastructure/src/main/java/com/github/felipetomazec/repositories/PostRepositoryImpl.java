package com.github.felipetomazec.repositories;

import com.github.felipetomazec.entities.Post;
import com.github.felipetomazec.repositories.entities.PostJpaEntity;
import com.github.felipetomazec.repositories.interfaces.PostJPARepository;
import com.github.felipetomazec.usecases.createpost.dependencies.CreatePostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements CreatePostRepository {

    private final PostJPARepository repository;

    @Override
    public void create(Post post) {
        repository.save(PostJpaEntity.from(post));
    }
}
