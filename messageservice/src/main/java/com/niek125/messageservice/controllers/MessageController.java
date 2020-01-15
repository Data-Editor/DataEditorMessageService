package com.niek125.messageservice.controllers;

import com.niek125.messageservice.models.Message;
import com.niek125.messageservice.repository.MessageRepo;
import com.niek125.messageservice.token.TokenChecker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/chat")
public class MessageController {
    private final Logger logger = LoggerFactory.getLogger(MessageController.class);
    private final MessageRepo messageRepo;
    private final TokenChecker tokenChecker;

    @Autowired
    public MessageController(MessageRepo messageRepo, TokenChecker tokenChecker) {
        this.messageRepo = messageRepo;
        this.tokenChecker = tokenChecker;
    }

    @GetMapping("/messages/{projectid}")
    public List<Message> getMessages(@PathVariable("projectid") String projectid, @RequestHeader("Authorization") String token) {
        if (!tokenChecker.hasPermission(token, projectid)) {
            logger.info("no permission");
            return new ArrayList<>();
        }
        logger.info("getting messages");
        final List<Message> messages = messageRepo.findMessagesByProjectid(projectid);
        logger.info("returning messages");
        return messages;
    }
}
