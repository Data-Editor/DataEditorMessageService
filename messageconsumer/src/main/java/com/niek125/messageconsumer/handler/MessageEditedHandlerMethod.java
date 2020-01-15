package com.niek125.messageconsumer.handler;

import com.niek125.messageconsumer.events.MessageEditedEvent;
import com.niek125.messageconsumer.repository.MessageRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageEditedHandlerMethod extends HandlerMethod<MessageEditedEvent> {
    private final Logger logger = LoggerFactory.getLogger(MessageEditedHandlerMethod.class);
    private final MessageRepo messageRepo;

    @Autowired
    public MessageEditedHandlerMethod(MessageRepo messageRepo) {
        super(MessageEditedEvent.class);
        this.messageRepo = messageRepo;
    }

    @Override
    public void handle(MessageEditedEvent event) {
        logger.info("saving message");
        messageRepo.save(event.getMessage());
    }
}
