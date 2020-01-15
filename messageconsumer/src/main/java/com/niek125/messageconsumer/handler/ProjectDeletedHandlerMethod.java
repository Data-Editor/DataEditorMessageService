package com.niek125.messageconsumer.handler;

import com.niek125.messageconsumer.events.ProjectDeletedEvent;
import com.niek125.messageconsumer.repository.MessageRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProjectDeletedHandlerMethod extends HandlerMethod<ProjectDeletedEvent> {
    private final Logger logger = LoggerFactory.getLogger(ProjectDeletedHandlerMethod.class);
    private final MessageRepo messageRepo;

    @Autowired
    public ProjectDeletedHandlerMethod(MessageRepo messageRepo) {
        super(ProjectDeletedEvent.class);
        this.messageRepo = messageRepo;
    }

    @Override
    public void handle(ProjectDeletedEvent event) {
        logger.info("deleting messages for projectid: {}", event.getProjectid());
        messageRepo.deleteByProjectid(event.getProjectid());
    }
}
