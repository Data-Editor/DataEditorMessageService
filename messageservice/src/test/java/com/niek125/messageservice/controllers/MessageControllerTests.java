package com.niek125.messageservice.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.niek125.messageservice.models.Message;
import com.niek125.messageservice.repository.MessageRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;

@ActiveProfiles("test")
@SpringBootTest
public class MessageControllerTests {
    @Autowired
    private MessageController messageController;
    @Autowired
    private MessageRepo messageRepo;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getMessagesTest() throws JsonProcessingException {
        final String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzUxMiJ9.eyJ1aWQiOiJtOWx2V3VIeEpUZ0FPZjR1Z0VjbWVhNnNDMHYxIiwidW5tIjoiUmlkZGxlIEZvcm0iLCJwbXMiOiJbe1wicHJvamVjdGlkXCI6XCIwMWZiNzkwMC0zMTk0LTExZWEtYTMyNi0yNzE2NzdkZWIzNDRcIixcInJvbGVcIjpcIk9XTkVSXCJ9LHtcInByb2plY3RpZFwiOlwiMDAwYWZiYTAtMzEzYy0xMWVhLWE1NjYtMWZkNTMxODkxOWQwXCIsXCJyb2xlXCI6XCJPV05FUlwifSx7XCJwcm9qZWN0aWRcIjpcIjY5NWJiNTkwLTMxNjQtMTFlYS1iNDdiLWVkM2U0ODA0OTE2YlwiLFwicm9sZVwiOlwiT1dORVJcIn1dIiwiaXNzIjoiZGF0YS1lZGl0b3ItdG9rZW4tc2VydmljZSIsInBmcCI6Imh0dHBzOi8vbGg1Lmdvb2dsZXVzZXJjb250ZW50LmNvbS8taEVmb2tSanlZZVEvQUFBQUFBQUFBQUkvQUFBQUFBQUFBQUEvQUNIaTNyZjFVYW1pT1RzQ0lleDVNOFdKMzdQbVpHNTU5dy9waG90by5qcGciLCJleHAiOjE1ODAxNzcxOTAsImlhdCI6MTU3OTA4MzE4NSwianRpIjoiZjEzYTcxZGMtY2U0Yy00ODA2LWEzMjAtMzU5NjY2M2FkZDQ4In0.UQ-SMX6ECdXSeDYqnQiTwi-_9srzLY58X-GlBf92maHc_r2UzBcWGnVQ88jMiBKQxT32BzY0DJVYpVW9BsQOHk3irE4VN_ISuPg70B3Ob5Nmbs6spkSvJSX6vk-bZ90h6vMjUukrOBxxOGAHfvqhwSqJOUgZIRx_U1XavQtDWg8";
        final String projectid = "000afba0-313c-11ea-a566-1fd5318919d0";
        final Message messageP = new Message("test123",projectid,
                "user123","this is a test", "yesterday");
        final Message messageP2 = new Message("test12345",projectid,
                "user12345","this is a test2", "yesterday222");
        final Message messageN = new Message("test987", "1234",
                "user12345","this is a test2", "yesterday222");

        messageRepo.save(messageP);
        messageRepo.save(messageP2);
        messageRepo.save(messageN);
        final List<Message> messages = messageController.getMessages(projectid, token);

        assertSame(2, messages.size());
        assertSame("test123", messages.get(0).getMessageid());
        assertSame("test12345", messages.get(1).getMessageid());
    }
}
