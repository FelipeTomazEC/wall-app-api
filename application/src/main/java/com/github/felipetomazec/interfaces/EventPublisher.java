package com.github.felipetomazec.interfaces;

public interface EventPublisher <DomainEvent> {
    void publish(DomainEvent event);
}
