package com.softeco.examples.services.common.components.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softeco.examples.services.common.exception.MapException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.bus.event.RemoteApplicationEvent;

@Getter
@Setter
@ToString
@Slf4j
public class BaseEvent<P> extends RemoteApplicationEvent {
    private static final Object TRANSIENT_SOURCE = new Object();

    private String commandId;
    private EventDomainType domainType;
    protected String eventType;
    protected String payload;

    public BaseEvent(){
        super();
        this.eventType = this.getClass().getName();
    }

    public BaseEvent(Object source){
        super();
        this.eventType = this.getClass().getName();
    }

    public BaseEvent(String commandId, EventDomainType domainType, P payload) throws MapException {
        this();
        setCommandId(commandId);
        setDomainType(domainType);
        try {
            setPayload(payloadAsJsonStr(payload));
        } catch (JsonProcessingException ex){
            log.error(ex.getMessage(), ex);
            throw new MapException(ex.getMessage());
        }
    }

    protected String payloadAsJsonStr(P payload) throws JsonProcessingException {
        return payload == null ? null :
                getObjectMapper().writeValueAsString(payload);
    }

    protected ObjectMapper getObjectMapper(){
        return new ObjectMapper();
    }


}
