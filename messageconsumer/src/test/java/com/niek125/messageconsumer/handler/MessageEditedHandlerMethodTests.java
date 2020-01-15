package com.niek125.messageconsumer.handler;

import com.niek125.messageconsumer.events.MessageEditedEvent;
import com.niek125.messageconsumer.models.Message;
import com.niek125.messageconsumer.repository.MessageRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
public class MessageEditedHandlerMethodTests {
    @Autowired
    private MessageEditedHandlerMethod handlerMethod;
    @Autowired
    private MessageRepo messageRepo;

    @Test
    public void editMessageTest() {
        final String oldContent = "old";
        final String newContent = "new";
        final String id = "test123";
        final Message message = new Message(id, "testclass", oldContent,
                "now", "12");
        final MessageEditedEvent event = new MessageEditedEvent();
        event.setMessage(message);

        messageRepo.save(message);
        message.setContent(newContent);
        handlerMethod.handle(event);
        final Message dbMessage = messageRepo.getOne(id);

        assertNotSame(oldContent, dbMessage.getContent());
        assertSame(message.getContent(), dbMessage.getContent());
        assertSame(message.getProjectid(), dbMessage.getProjectid());
        assertSame(message.getSenderid(), dbMessage.getSenderid());
        assertSame(message.getSendtime(), dbMessage.getSendtime());
    }

    @Test
    public void processType() {
        assertSame(MessageEditedEvent.class, handlerMethod.getHandlingType());
    }
}
