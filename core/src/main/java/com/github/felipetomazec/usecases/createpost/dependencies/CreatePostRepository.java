package com.github.felipetomazec.usecases.createpost.dependencies;

import com.github.felipetomazec.entities.Post;

public interface CreatePostRepository {
    void create(Post post);
}
