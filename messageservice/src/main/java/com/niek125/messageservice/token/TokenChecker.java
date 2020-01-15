package com.niek125.messageservice.token;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.niek125.messageservice.models.Permission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class TokenChecker {
    private final Logger logger = LoggerFactory.getLogger(TokenChecker.class);
    private final ObjectMapper objectMapper;
    private final JWTVerifier jwtVerifier;

    @Autowired
    public TokenChecker(ObjectMapper objectMapper, JWTVerifier jwtVerifier) {
        this.objectMapper = objectMapper;
        this.jwtVerifier = jwtVerifier;
    }

    public boolean hasPermission(String token, String projectid) {
        try {
            logger.info("verifying token");
            final DecodedJWT jwt = jwtVerifier.verify(token.replace("Bearer ", ""));
            final Permission[] perms = objectMapper.readValue(((jwt.getClaims()).get("pms")).asString(), Permission[].class);
            return Arrays.stream(perms).anyMatch(p -> p.getProjectid().equals(projectid));
        } catch (JsonProcessingException e) {
            return false;
        }
    }
}
