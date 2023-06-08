package com.github.felipetomazec.adapters;

import com.github.felipetomazec.events.PostCreatedEvent;
import com.github.felipetomazec.interfaces.EventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PostCreatedEventPublisher implements EventPublisher<PostCreatedEvent> {

    @Override
    public void publish(PostCreatedEvent event) {
        log.info("Publishing  event 'PostCreated'. id={} author={}", event.postId(), event.authorId());
    }
}
