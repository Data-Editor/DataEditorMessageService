package com.niek125.messageconsumer.kafka;

import com.niek125.messageconsumer.handler.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageConsumer {
    private final Logger logger = LoggerFactory.getLogger(KafkaMessageConsumer.class);
    private final EventHandler eventHandler;

    @Autowired
    public KafkaMessageConsumer(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    @KafkaListener(topics = {"message", "project"}, groupId = "message-consumer",
            autoStartup = "${com.niek125.kafka.enabled}")
    public void consume(String message) {
        logger.info("received message: {}", message);
        eventHandler.processMessage(message);
        logger.info("successfully processed message");
    }
}
