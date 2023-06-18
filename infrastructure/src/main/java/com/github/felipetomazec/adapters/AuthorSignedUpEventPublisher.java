package com.github.felipetomazec.adapters;

import com.github.felipetomazec.events.AuthorSignedUpEvent;
import com.github.felipetomazec.interfaces.EventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthorSignedUpEventPublisher implements EventPublisher<AuthorSignedUpEvent> {
    @Override
    public void publish(AuthorSignedUpEvent event) {
        log.info("A new author has signed up to the platform. email ={}", event.email());
    }
}
