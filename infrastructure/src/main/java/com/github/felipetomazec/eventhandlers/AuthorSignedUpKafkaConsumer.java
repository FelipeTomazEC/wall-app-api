package com.github.felipetomazec.eventhandlers;

import com.github.felipetomazec.adapters.AuthorSignedUpEventPublisher;
import com.github.felipetomazec.events.authorsignup.AuthorSignedUpEvent;
import com.github.felipetomazec.interfaces.EventHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class AuthorSignedUpKafkaConsumer {

    private final Set<EventHandler<AuthorSignedUpEvent>> eventHandlers;

    @KafkaListener(topics = AuthorSignedUpEventPublisher.AUTHOR_SIGNED_UP_TOPIC)
    public void consume(AuthorSignedUpEvent event) {
        eventHandlers.forEach(handler -> handler.handle(event));
    }
}
