package com.niek125.messageservice.controllers;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.niek125.messageservice.models.Message;
import com.niek125.messageservice.models.Permission;
import com.niek125.messageservice.repository.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/chat")
public class MessageController {
    private final MessageRepo messageRepo;
    private final JWTVerifier jwtVerifier;
    private final ObjectMapper objectMapper;

    @Autowired
    public MessageController(MessageRepo messageRepo, JWTVerifier jwtVerifier, ObjectMapper objectMapper) {
        this.messageRepo = messageRepo;
        this.jwtVerifier = jwtVerifier;
        this.objectMapper = objectMapper;
    }

    @RequestMapping("/messages/{projectid}")
    public List<Message> getMessages(@PathVariable("projectid") String projectid, @RequestHeader("Authorization") String token){
        try {
            final DecodedJWT jwt = jwtVerifier.verify(token.replace("Bearer ", ""));
            Permission[] perms = objectMapper.readValue(((jwt.getClaims()).get("pms")).asString(), Permission[].class);
            if (!Arrays.stream(perms).filter(p -> p.getProjectid().equals(projectid)).findFirst().isPresent()) {
                return null;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return messageRepo.findMessagesByProjectid(projectid);
    }
}
