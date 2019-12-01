package com.Niek125.messageservice.kafka;

import com.Niek125.messageservice.models.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {
    private final Logger logger = LoggerFactory.getLogger(Consumer.class);
    private final ObjectMapper mapper = new ObjectMapper();

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
                            System.out.println("working header");
                            //add to db
                            break;
                        case UPDATE:
                            //update db
                            break;
                        case DELETE:
                            //remove from db
                            break;
                    }
                    break;
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
