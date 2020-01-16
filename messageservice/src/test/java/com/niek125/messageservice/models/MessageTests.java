package com.niek125.messageservice.models;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertSame;

@ActiveProfiles("test")
public class MessageTests {

    @Test
    public void getterTest(){
        final String messageid = "234";
        final String projectid = "23qewd4";
        final String content= "2safdgh34";
        final String senderid = "qewret";
        final String sendtime = "w2edrfdgbfd";

        final Message message = new Message(messageid, projectid, senderid, content, sendtime);

        assertSame(messageid, message.getMessageid());
        assertSame(projectid, message.getProjectid());
        assertSame(content, message.getContent());
        assertSame(senderid, message.getSenderid());
        assertSame(sendtime, message.getSendtime());
    }
}
