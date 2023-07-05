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
    public CreatePostOutput execute(CreatePostInput input) {
        var id = UUID.randomUUID();
        var authorId = input.getAuthorId();

        var post = Post.builder()
                .createdAt(LocalDateTime.now())
                .id(id)
                .authorId(input.getAuthorId())
                .reactions(new HashSet<>())
                .content(input.getContent())
                .build();

        postRepository.create(post);

        eventPublisher.publish(new PostCreatedEvent(id.toString(), authorId.toString()));

        return presenter.success(post);
    }
}
