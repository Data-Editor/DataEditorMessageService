package com.niek125.messageconsumer.handler;

import com.niek125.messageconsumer.events.DataEditorEvent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
public class UnrecognizedEventHandlerMethodTests {
    @Autowired
    private UnrecognizedEventHandlerMethod handlerMethod;

    @Test
    public void doesNotThrowException(){
        assertDoesNotThrow(() -> handlerMethod.handle(new DataEditorEvent()));
    }

    @Test
    public void processType() {
        assertSame(DataEditorEvent.class, handlerMethod.getHandlingType());
    }
}
