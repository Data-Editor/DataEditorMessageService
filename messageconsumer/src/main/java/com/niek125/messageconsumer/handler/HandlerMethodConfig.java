package com.niek125.messageconsumer.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class HandlerMethodConfig {
    @Bean
    @Autowired
    public List<HandlerMethod> handlerMethods(MessagePostedHandlerMethod messagePostedHandlerMethod,
                                              MessageEditedHandlerMethod messageEditedHandlerMethod,
                                              MessageDeletedHandlerMethod messageDeletedHandlerMethod,
                                              ProjectDeletedHandlerMethod projectDeletedHandlerMethod) {
        final List<HandlerMethod> methods = new ArrayList<>();
        methods.add(messagePostedHandlerMethod);
        methods.add(messageEditedHandlerMethod);
        methods.add(messageDeletedHandlerMethod);
        methods.add(projectDeletedHandlerMethod);
        return methods;
    }
}
