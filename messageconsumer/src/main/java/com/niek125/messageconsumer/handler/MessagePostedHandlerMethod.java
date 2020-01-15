package com.niek125.messageconsumer.handler;

import com.niek125.messageconsumer.events.MessagePostedEvent;
import com.niek125.messageconsumer.repository.MessageRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessagePostedHandlerMethod extends HandlerMethod<MessagePostedEvent> {
    private final Logger logger = LoggerFactory.getLogger(MessagePostedHandlerMethod.class);
    private final MessageRepo messageRepo;

    @Autowired
    public MessagePostedHandlerMethod(MessageRepo messageRepo) {
        super(MessagePostedEvent.class);
        this.messageRepo = messageRepo;
    }

    @Override
    public void handle(MessagePostedEvent event) {
        logger.info("saving message");
        messageRepo.save(event.getMessage());
    }
}
