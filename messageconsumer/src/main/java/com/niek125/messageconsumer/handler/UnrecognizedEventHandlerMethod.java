package com.niek125.messageconsumer.handler;

import com.niek125.messageconsumer.events.DataEditorEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UnrecognizedEventHandlerMethod extends HandlerMethod<DataEditorEvent> {
    private final Logger logger = LoggerFactory.getLogger(UnrecognizedEventHandlerMethod.class);

    protected UnrecognizedEventHandlerMethod() {
        super(DataEditorEvent.class);
    }


    @Override
    public void handle(DataEditorEvent event) {
        logger.info("could not find: {}", event.getClass().getSimpleName());
    }
}
