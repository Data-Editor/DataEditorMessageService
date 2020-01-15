package com.niek125.messageconsumer.kafka;

import com.niek125.messageconsumer.models.Message;
import com.niek125.messageconsumer.repository.MessageRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
public class KafkaMessageConsumerTests {
    @Autowired
    private KafkaMessageConsumer messageConsumer;
    @Autowired
    private MessageRepo messageRepo;

    @Test
    public void consumeMessageTest(){
        final String message = "{\"class\":\"MessagePostedEvent\",\"event\":\"{\\\"creator\\\":\\\"socket-consumer\\\",\\\"interest\\\":\\\"01fb7900-3194-11ea-a326-271677deb344\\\",\\\"message\\\":{\\\"messageid\\\":\\\"a1a4cf80-3575-11ea-b3c4-317c39cc3db1\\\",\\\"senderid\\\":\\\"m9lvWuHxJTgAOf4ugEcmea6sC0v1\\\",\\\"content\\\":\\\"Hi\\\",\\\"sendtime\\\":\\\"12-01-2020 20:56\\\",\\\"projectid\\\":\\\"01fb7900-3194-11ea-a326-271677deb344\\\"}}\"}";

        messageConsumer.consume(message);
        final Message dbMessage = messageRepo.getOne("a1a4cf80-3575-11ea-b3c4-317c39cc3db1");

        assertEquals("Hi", dbMessage.getContent());
        assertEquals("01fb7900-3194-11ea-a326-271677deb344", dbMessage.getProjectid());
        assertEquals("m9lvWuHxJTgAOf4ugEcmea6sC0v1", dbMessage.getSenderid());
        assertEquals("12-01-2020 20:56", dbMessage.getSendtime());
    }

    @Test
    public void consumeInvalidMessage(){
        final String message = "{\"class\":\"IDoNotExist\",\"event\":\"{\\\"creator\\\":\\\"socket-consumer\\\",\\\"interest\\\":\\\"01fb7900-3194-11ea-a326-271677deb344\\\",\\\"message\\\":{\\\"messageid\\\":\\\"a1a4cf80-3575-11ea-b3c4-317c39cc3db1\\\",\\\"senderid\\\":\\\"m9lvWuHxJTgAOf4ugEcmea6sC0v1\\\",\\\"content\\\":\\\"Hi\\\",\\\"sendtime\\\":\\\"12-01-2020 20:56\\\",\\\"projectid\\\":\\\"01fb7900-3194-11ea-a326-271677deb344\\\"}}\"}";

        messageConsumer.consume(message);
        final boolean exists = messageRepo.existsById("a1a4cf80-3575-11ea-b3c4-317c39cc3db1");

        assertFalse(exists);
    }
}
