package com.github.felipetomazec.adapters;

import com.github.felipetomazec.events.AuthorSignedUpEvent;
import com.github.felipetomazec.interfaces.EventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthorSignedUpEventPublisher implements EventPublisher<AuthorSignedUpEvent> {

    public static final String AUTHOR_SIGNED_UP_TOPIC = "author-signed-up";
    private final KafkaTemplate<String, AuthorSignedUpEvent> kafkaClient;


    @Override
    public void publish(AuthorSignedUpEvent event) {
        kafkaClient.send(AUTHOR_SIGNED_UP_TOPIC, event.email(), event);
    }
}
