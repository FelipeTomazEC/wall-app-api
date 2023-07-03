package com.github.felipetomazec.config;

import com.github.felipetomazec.adapters.AuthorSignedUpEventPublisher;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaContextConfig {
    @Bean
    public NewTopic topic() {
        return TopicBuilder.name(AuthorSignedUpEventPublisher.AUTHOR_SIGNED_UP_TOPIC)
                .build();
    }
}
