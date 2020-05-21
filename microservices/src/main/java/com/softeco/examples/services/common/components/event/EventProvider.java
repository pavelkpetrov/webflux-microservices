package com.softeco.examples.services.common.components.event;

import org.springframework.context.ApplicationListener;

public interface EventProvider {

    void sendEvent(BaseEvent event);
    void subscribeEvent(ApplicationListener listener);

}
