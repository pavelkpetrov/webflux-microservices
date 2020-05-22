package com.softeco.examples.services.order.event;

import com.softeco.examples.services.common.components.event.BaseEvent;
import com.softeco.examples.services.common.components.event.BussEventListener;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

@EnableBinding(Sink.class)
@Component
public class OrderEventListener  implements BussEventListener {

    @StreamListener(target = Sink.INPUT)
    public void handleMessageStr(BaseEvent event) throws Exception {
        System.out.println("Event: " + event);
    }

}

