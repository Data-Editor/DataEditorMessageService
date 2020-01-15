package com.niek125.messageconsumer.handler;

import com.niek125.messageconsumer.events.MessageDeletedEvent;
import com.niek125.messageconsumer.models.Message;
import com.niek125.messageconsumer.repository.MessageRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
public class MessageDeletedHandlerMethodTests {
    @Autowired
    private MessageDeletedHandlerMethod handlerMethod;
    @Autowired
    private MessageRepo messageRepo;

    @Test
    public void deletedMessageTest() {
        final String messageid = "deltedtest";
        final Message message = new Message(messageid, "testclass", "this is a unit test",
                "now", "12");
        final MessageDeletedEvent event = new MessageDeletedEvent();
        event.setMessageid(messageid);

        messageRepo.save(message);
        final boolean existsBefore = messageRepo.existsById(messageid);
        handlerMethod.handle(event);
        final boolean existsAfter = messageRepo.existsById(messageid);

        assertTrue(existsBefore);
        assertFalse(existsAfter);
    }

    @Test
    public void processType() {
        assertSame(MessageDeletedEvent.class, handlerMethod.getHandlingType());
    }
}
