package com.github.felipetomazec.controllers;

import com.github.felipetomazec.annotations.Requester;
import com.github.felipetomazec.dtos.RequesterInfo;
import com.github.felipetomazec.requests.CreatePostRequest;
import com.github.felipetomazec.usecases.createpost.CreatePostUseCase;
import com.github.felipetomazec.usecases.createpost.dtos.CreatePostInput;
import com.github.felipetomazec.usecases.createpost.dtos.CreatePostOutput;
import com.github.felipetomazec.usecases.retrieveallposts.RetrieveAllPostsUseCase;
import com.github.felipetomazec.usecases.retrieveallposts.dtos.RetrieveAllPostsOutput;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/posts")
public class PostController {
    private final CreatePostUseCase createPostUseCase;
    private final RetrieveAllPostsUseCase retrieveAllPostsUseCase;

    @PostMapping("/")
    public CreatePostOutput createPost(
            @Valid @RequestBody CreatePostRequest request,
            @Requester RequesterInfo requester
    ) {
        var input = CreatePostInput.builder()
                .authorId(requester.id())
                .content(request.content())
                .build();

        return createPostUseCase.execute(input);
    }

    @GetMapping("/")
    public RetrieveAllPostsOutput retrieveAllPosts() {
        return retrieveAllPostsUseCase.execute(null);
    }
}
