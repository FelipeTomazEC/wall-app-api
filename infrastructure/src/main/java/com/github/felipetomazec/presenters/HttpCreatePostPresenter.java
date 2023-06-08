package com.github.felipetomazec.presenters;

import com.github.felipetomazec.entities.Post;
import com.github.felipetomazec.usecases.createpost.dependencies.CreatePostPresenter;
import com.github.felipetomazec.usecases.createpost.dtos.CreatePostOutput;
import org.springframework.stereotype.Component;

@Component
public class HttpCreatePostPresenter implements CreatePostPresenter {

    @Override
    public CreatePostOutput success(Post post) {
        return CreatePostOutput.builder()
                .postId(post.getId())
                .build();
    }
}
