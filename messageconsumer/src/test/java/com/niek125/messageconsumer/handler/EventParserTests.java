package com.niek125.messageconsumer.handler;

import com.niek125.messageconsumer.events.DataEditorEvent;
import com.niek125.messageconsumer.events.MessagePostedEvent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
public class EventParserTests {
    @Autowired
    private EventParser eventParser;

    @Test
    public void parseTest(){
        final String message = "{\"class\":\"MessagePostedEvent\",\"event\":\"{\\\"creator\\\":\\\"socket-consumer\\\",\\\"interest\\\":\\\"01fb7900-3194-11ea-a326-271677deb344\\\",\\\"message\\\":{\\\"messageid\\\":\\\"a1a4cf80-3575-11ea-b3c4-317c39cc3db1\\\",\\\"senderid\\\":\\\"m9lvWuHxJTgAOf4ugEcmea6sC0v1\\\",\\\"content\\\":\\\"Hi\\\",\\\"sendtime\\\":\\\"12-01-2020 20:56\\\",\\\"projectid\\\":\\\"01fb7900-3194-11ea-a326-271677deb344\\\"}}\"}";

        final DataEditorEvent event = eventParser.parseToEvent(message);

        assertSame(MessagePostedEvent.class, event.getClass());
    }

    @Test
    public void invalidEvent(){
        final String message = "{\"class\":\"MessagePostedEvent\",\"event\":\"{\\\"xxx\\\":\\\"xxx\\\",\\\"creator\\\":\\\"socket-consumer\\\",\\\"interest\\\":\\\"01fb7900-3194-11ea-a326-271677deb344\\\",\\\"message\\\":{\\\"messageid\\\":\\\"a1a4cf80-3575-11ea-b3c4-317c39cc3db1\\\",\\\"senderid\\\":\\\"m9lvWuHxJTgAOf4ugEcmea6sC0v1\\\",\\\"content\\\":\\\"Hi\\\",\\\"sendtime\\\":\\\"12-01-2020 20:56\\\",\\\"projectid\\\":\\\"01fb7900-3194-11ea-a326-271677deb344\\\"}}\"}";

        final DataEditorEvent event = eventParser.parseToEvent(message);

        assertSame(DataEditorEvent.class, event.getClass());
    }

    @Test
    public void notExistingEventTest(){
        final String message = "{\"class\":\"IDoNotExist\",\"event\":\"{\\\"creator\\\":\\\"socket-consumer\\\",\\\"interest\\\":\\\"01fb7900-3194-11ea-a326-271677deb344\\\",\\\"message\\\":{\\\"messageid\\\":\\\"a1a4cf80-3575-11ea-b3c4-317c39cc3db1\\\",\\\"senderid\\\":\\\"m9lvWuHxJTgAOf4ugEcmea6sC0v1\\\",\\\"content\\\":\\\"Hi\\\",\\\"sendtime\\\":\\\"12-01-2020 20:56\\\",\\\"projectid\\\":\\\"01fb7900-3194-11ea-a326-271677deb344\\\"}}\"}";

        final DataEditorEvent event = eventParser.parseToEvent(message);

        assertSame(DataEditorEvent.class, event.getClass());
    }
}
