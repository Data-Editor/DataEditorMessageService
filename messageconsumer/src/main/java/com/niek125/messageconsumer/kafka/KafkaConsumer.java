package com.niek125.messageconsumer.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.niek125.messageconsumer.models.Action;
import com.niek125.messageconsumer.models.KafkaHeader;
import com.niek125.messageconsumer.models.KafkaMessage;
import com.niek125.messageconsumer.models.Message;
import com.niek125.messageconsumer.repository.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    private final ObjectMapper objectMapper;
    private final MessageRepo messageRepo;

    @Autowired
    public KafkaConsumer(ObjectMapper objectMapper, MessageRepo messageRepo) {
        this.objectMapper = objectMapper;
        this.messageRepo = messageRepo;
    }

    @KafkaListener(topics = "message", groupId = "message-consumer")
    public void consume(String message) {
        System.out.println(message);
        try {
            final String[] pay = message.split("\n");
            final DocumentContext doc = JsonPath.parse(pay[0]);
            final KafkaMessage kafkaMessage = new KafkaMessage(new KafkaHeader(Action.valueOf(doc.read("$.action")), doc.read("$.payload")), pay[1]);
            final Message msg = objectMapper.readValue(kafkaMessage.getPayload(), Message.class);
            switch (kafkaMessage.getKafkaHeader().getAction()) {
                case CREATE:
                case UPDATE:
                    messageRepo.save(msg);
                    break;
                case DELETE:
                    messageRepo.deleteById(msg.getMessageid());
                    break;
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
