package com.github.felipetomazec.usecases.createpost;

import com.github.felipetomazec.entities.Post;
import com.github.felipetomazec.events.PostCreatedEvent;
import com.github.felipetomazec.interfaces.EventPublisher;
import com.github.felipetomazec.usecases.createpost.dependencies.CreatePostPresenter;
import com.github.felipetomazec.usecases.createpost.dtos.CreatePostInput;
import com.github.felipetomazec.usecases.createpost.dependencies.CreatePostRepository;
import com.github.felipetomazec.interfaces.UseCase;
import com.github.felipetomazec.usecases.createpost.dtos.CreatePostOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreatePostUseCase implements UseCase<CreatePostInput, CreatePostOutput> {

    private final CreatePostRepository postRepository;
    private final CreatePostPresenter presenter;
    private final EventPublisher<PostCreatedEvent> eventPublisher;

    @Override
    public CreatePostOutput execute(CreatePostInput createPostInput) {
        var id = UUID.randomUUID().toString();
        var authorId = createPostInput.getAuthorId();

        var post = Post.builder()
                .createdAt(LocalDateTime.now())
                .id(id)
                .authorId(createPostInput.getAuthorId())
                .comments(new HashSet<>())
                .reactions(new HashSet<>())
                .content(createPostInput.getContent())
                .build();

        postRepository.create(post);

        eventPublisher.publish(new PostCreatedEvent(id, authorId));

        return presenter.success(post);
    }
}
