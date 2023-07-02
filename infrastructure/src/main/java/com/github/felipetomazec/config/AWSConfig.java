package com.github.felipetomazec.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "wall-app.aws")
public class AWSConfig {
    private String accessKey;
    private String secretKey;
    private S3 s3;

    @Data
    public static class S3 {
        private String serviceEndpoint;
    }
}
