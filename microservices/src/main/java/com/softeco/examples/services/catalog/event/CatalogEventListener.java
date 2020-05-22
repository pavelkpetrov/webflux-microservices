package com.softeco.examples.services.catalog.event;

import com.softeco.examples.services.common.components.event.BussEventListener;
import com.softeco.examples.services.common.event.OrderFormEvent;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

@EnableBinding(Sink.class)
@Component
public class CatalogEventListener implements BussEventListener {

    @StreamListener(target = Sink.INPUT)
    public void handleMessageStr(OrderFormEvent event) throws Exception {
        System.out.println("Event: " + event);
    }

}