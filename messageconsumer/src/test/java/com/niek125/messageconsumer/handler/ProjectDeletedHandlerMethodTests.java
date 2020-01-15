package com.niek125.messageconsumer.handler;

import com.niek125.messageconsumer.events.ProjectDeletedEvent;
import com.niek125.messageconsumer.models.Message;
import com.niek125.messageconsumer.repository.MessageRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
public class ProjectDeletedHandlerMethodTests {
    @Autowired
    private ProjectDeletedHandlerMethod handlerMethod;
    @Autowired
    private MessageRepo messageRepo;

    @Test
    public void removeAllMessagesProject(){
        final String deletedProject = "13";
        final String deletedid = "deltedtest345678";
        final Message deletedMessage = new Message(deletedid, "testclass", "this is a unit test",
                "now", deletedProject);
        final String deletedid2 = "deltedtest98765";
        final Message deletedMessage2 = new Message(deletedid2, "testclass", "this is a unit test",
                "now", deletedProject);
        final String aliveid = "alivetest98765";
        final Message aliveMessage = new Message(aliveid, "testclass", "this is a unit test",
                "now", "12");
        final String aliveid2 = "alivetest1267";
        final Message aliveMessage2 = new Message(aliveid2, "testclass", "this is a unit test",
                "now", "12");
        final ProjectDeletedEvent event = new ProjectDeletedEvent();
        event.setProjectid(deletedProject);

        messageRepo.save(deletedMessage);
        messageRepo.save(aliveMessage);
        messageRepo.save(aliveMessage2);
        messageRepo.save(deletedMessage2);
        handlerMethod.handle(event);
        final boolean del = messageRepo.existsById(deletedid);
        final boolean del2 = messageRepo.existsById(deletedid2);
        final boolean ali = messageRepo.existsById(aliveid);
        final boolean ali2 = messageRepo.existsById(aliveid2);

        assertTrue(ali);
        assertTrue(ali2);
        assertFalse(del);
        assertFalse(del2);
    }

    @Test
    public void processType() {
        assertSame(ProjectDeletedEvent.class, handlerMethod.getHandlingType());
    }
}
