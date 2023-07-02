package com.github.felipetomazec.usecases.createpost.dependencies;

import com.github.felipetomazec.entities.Post;
import com.github.felipetomazec.usecases.createpost.dtos.CreatePostOutput;

public interface CreatePostPresenter {
    CreatePostOutput success(Post post);
}
