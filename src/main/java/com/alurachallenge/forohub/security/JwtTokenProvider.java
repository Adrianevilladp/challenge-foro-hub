package com.alurachallenge.forohub.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Component
@Slf4j
public class JwtTokenProvider {
    private static final String ISSUER = "ForoHub";

    @Value("${forohub.security.key}")
    private String jwtSecret;

    public String generateToken(Authentication authentication) {
        UserPrincipal userAuthenticated = (UserPrincipal) authentication.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256(getSigningKey());
        try {
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(userAuthenticated.getEmail())
                    .withClaim("id", userAuthenticated.getId())
                    .withExpiresAt(generateExpirationDate())
                    .withIssuedAt(new Date())
                    .withJWTId(UUID.randomUUID().toString())
                    .withNotBefore(new Date(System.currentTimeMillis() + 1000L))
                    .sign(algorithm);
        } catch (Exception exception) {
            throw new RuntimeException("JWT creation error", exception);
        }
    }

    public boolean validateToken(String tokenJWT) {
        try {
            getVerifier().verify(tokenJWT);
            return true;
        } catch (JWTVerificationException exception) {
            log.error("JWT validation failed: {}", exception.getMessage());
            return false;
        }
    }

    public String getEmailFromUser(String tokenJWT) {
        return getVerifier()
                .verify(tokenJWT)
                .getSubject();
    }

    private byte[] getSigningKey() {
        return Base64
                .getDecoder()
                .decode(jwtSecret);
    }

    private JWTVerifier getVerifier() {
        Algorithm algorithm = Algorithm.HMAC256(getSigningKey());

        return JWT.require(algorithm)
                .withIssuer(ISSUER)
                .build();
    }

    private Date generateExpirationDate() {
        return Date.from(LocalDateTime
                .now()
                .plusHours(2)
                .toInstant(ZoneOffset.of("-05:00")));
    }

}
