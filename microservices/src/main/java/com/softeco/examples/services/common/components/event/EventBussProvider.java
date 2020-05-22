package com.softeco.examples.services.common.components.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;


@EnableBinding(Source.class)
@Component
@Profile({"ACCOUNT", "AGGREGATE", "CATALOG", "CUSTOMER", "GATEWAY", "ORDER"})
@Slf4j
public class EventBussProvider implements EventProvider {

    @Autowired
    private Source source;

    @Override
    public void sendEvent(BaseEvent event) {
        log.info("Event to send: {}", event);
        try {
            source.output().send(MessageBuilder.withPayload(event).build());
        } catch (Exception e){
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void subscribeEvent(ApplicationListener listener) {

    }

}
