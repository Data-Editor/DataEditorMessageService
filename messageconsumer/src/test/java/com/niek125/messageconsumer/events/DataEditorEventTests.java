package com.niek125.messageconsumer.events;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
public class DataEditorEventTests {
    @Test
    public void defaultFields(){
        final DataEditorEvent event = new DataEditorEvent();

        assertNull(event.getInterest());
        assertSame("message-consumer", event.getCreator());
    }
}
