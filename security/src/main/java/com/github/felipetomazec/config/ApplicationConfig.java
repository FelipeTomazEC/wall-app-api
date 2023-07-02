package com.github.felipetomazec.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@ConfigurationProperties(prefix = "wall-app.security")
@Configuration
@Data
public class ApplicationConfig {
    String key;
    String [] frontendOrigins;
    Expiration expiration;

    @Data
    public static class Expiration {
        Integer time;
        ExpirationUnit unit;
    }

    public enum ExpirationUnit {
        SECOND,
        MINUTE,
        HOUR,
        DAY,
        WEEK,
        MONTH,
        YEAR
    }

    public Long getExpirationTime() {
        var unitMultiplier = new HashMap<ExpirationUnit, Long>();
        unitMultiplier.put(ExpirationUnit.SECOND, 1000L);
        unitMultiplier.put(ExpirationUnit.MINUTE, unitMultiplier.get(ExpirationUnit.SECOND) * 60);
        unitMultiplier.put(ExpirationUnit.HOUR, unitMultiplier.get(ExpirationUnit.MINUTE) * 60);
        unitMultiplier.put(ExpirationUnit.DAY, unitMultiplier.get(ExpirationUnit.HOUR) * 24);
        unitMultiplier.put(ExpirationUnit.WEEK, unitMultiplier.get(ExpirationUnit.DAY) * 7);
        unitMultiplier.put(ExpirationUnit.MONTH, unitMultiplier.get(ExpirationUnit.DAY) * 30);
        unitMultiplier.put(ExpirationUnit.YEAR, unitMultiplier.get(ExpirationUnit.DAY) * 365);

        return expiration.time * unitMultiplier.get(expiration.unit);
    }
}
