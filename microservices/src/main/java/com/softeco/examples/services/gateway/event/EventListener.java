package com.softeco.examples.services.gateway.event;

import com.softeco.examples.services.common.components.event.BaseEvent;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

@EnableBinding(Sink.class)
@Component
public class EventListener {

    @StreamListener(target = Sink.INPUT)
    public void handleMessageStr(BaseEvent event) throws Exception {
        System.out.println("Event: " + event);
    }

}
