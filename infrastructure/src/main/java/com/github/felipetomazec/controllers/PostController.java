package com.github.felipetomazec.controllers;

import com.github.felipetomazec.requests.CreatePostRequest;
import com.github.felipetomazec.usecases.createpost.CreatePostUseCase;
import com.github.felipetomazec.usecases.createpost.dtos.CreatePostInput;
import com.github.felipetomazec.usecases.createpost.dtos.CreatePostOutput;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/posts")
public class PostController {
    private final CreatePostUseCase createPostUseCase;

    @PostMapping("/")
    public CreatePostOutput createPost(@Valid @RequestBody CreatePostRequest request) {
        var input = CreatePostInput.builder()
                .authorId("some-id-in")
                .content(request.getContent())
                .build();

        return createPostUseCase.execute(input);
    }
}
