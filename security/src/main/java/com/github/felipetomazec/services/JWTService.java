package com.github.felipetomazec.services;

import com.github.felipetomazec.config.ApplicationConfig;
import com.github.felipetomazec.entities.Credentials;
import com.github.felipetomazec.exceptions.InvalidJwtException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JWTService {

    private final ApplicationConfig config;

    public boolean isValid(String token) {
        var expirationDate = extractExpirationDate(token);
        var now = new Date(Instant.now().toEpochMilli());

        return expirationDate.after(now);
    }

    public String extractEmail(String token) {
        return extractClaim(token, (Claims::getSubject));
    }

    private Date extractExpirationDate(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String generateToken(Credentials credentials, Map<String, Object> extraClaims) {
        var nowInMillis = Instant.now().toEpochMilli();
        var expirationDateInMillis =  nowInMillis + config.getExpirationTime();

        return Jwts.builder()
                .addClaims(extraClaims)
                .setSubject(credentials.getEmail())
                .setIssuedAt(new Date(nowInMillis))
                .setExpiration(new Date(expirationDateInMillis))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSignInKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (MalformedJwtException | ClaimJwtException exception) {
            throw new InvalidJwtException();
        }
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimExtractor) {
        var claims = extractAllClaims(token);
        return claimExtractor.apply(claims);
    }

    private Key getSignInKey() {
        var keyBytes = Decoders.BASE64.decode(config.getKey());
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
