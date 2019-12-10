package com.Niek125.messageservice.kafka;

import com.Niek125.messageservice.models.Message;
import com.Niek125.messageservice.repository.MessageRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {
    private Logger logger = LoggerFactory.getLogger(Consumer.class);
    private ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private MessageRepo messageRepo;

    @KafkaListener(topics = "message", groupId = "message-consumer")
    public void consume(String message) {
        try {
            logger.info(String.format("$$ -> Consumed Message -> %s", message));
            String[] headerPay = message.split("\n");
            KafkaHeader header = mapper.readValue(headerPay[0], KafkaHeader.class);
            switch (header.getObject()) {
                case "message":
                    Message msg = mapper.readValue(headerPay[1], Message.class);
                    switch (header.getAction()) {
                        case CREATE:
                        case UPDATE:
                            messageRepo.save(msg);
                            break;
                        case DELETE:
                            messageRepo.deleteById(msg.getMessageid());
                            break;
                    }
                    break;
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
