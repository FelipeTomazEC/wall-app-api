package com.github.felipetomazec.events.authorsignup.dependencies;

public interface WelcomeEmailGenerator {
    String generate(String recipientName);
}
