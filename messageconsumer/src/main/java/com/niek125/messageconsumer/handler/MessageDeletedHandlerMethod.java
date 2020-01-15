package com.niek125.messageconsumer.handler;

import com.niek125.messageconsumer.events.MessageDeletedEvent;
import com.niek125.messageconsumer.repository.MessageRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageDeletedHandlerMethod extends HandlerMethod<MessageDeletedEvent> {
    private final Logger logger = LoggerFactory.getLogger(MessageDeletedHandlerMethod.class);
    private final MessageRepo messageRepo;

    @Autowired
    public MessageDeletedHandlerMethod(MessageRepo messageRepo) {
        super(MessageDeletedEvent.class);
        this.messageRepo = messageRepo;
    }

    @Override
    public void handle(MessageDeletedEvent event) {
        logger.info("deleting message");
        messageRepo.deleteById(event.getMessageid());
    }
}
