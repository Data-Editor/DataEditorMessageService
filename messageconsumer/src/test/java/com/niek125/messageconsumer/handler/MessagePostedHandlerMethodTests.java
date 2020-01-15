package com.niek125.messageconsumer.handler;

import com.niek125.messageconsumer.events.MessagePostedEvent;
import com.niek125.messageconsumer.models.Message;
import com.niek125.messageconsumer.repository.MessageRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertSame;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
public class MessagePostedHandlerMethodTests {
    @Autowired
    private MessageRepo messageRepo;
    @Autowired
    private MessagePostedHandlerMethod handlerMethod;

    @Test
    public void AddsToDbTest() {
        final Message message = new Message("test", "testclass", "this is a unit test",
                "now", "12");
        final MessagePostedEvent event = new MessagePostedEvent();
        event.setMessage(message);

        handlerMethod.handle(event);
        final Message dbMessage = messageRepo.getOne(message.getMessageid());

        assertSame(message.getContent(), dbMessage.getContent());
        assertSame(message.getProjectid(), dbMessage.getProjectid());
        assertSame(message.getSenderid(), dbMessage.getSenderid());
        assertSame(message.getSendtime(), dbMessage.getSendtime());
    }

    @Test
    public void processType() {
        assertSame(MessagePostedEvent.class, handlerMethod.getHandlingType());
    }
}
