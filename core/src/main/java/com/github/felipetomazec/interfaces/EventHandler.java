package com.github.felipetomazec.interfaces;

public interface EventHandler<DomainEvent>{
    void handle(DomainEvent event);
}
