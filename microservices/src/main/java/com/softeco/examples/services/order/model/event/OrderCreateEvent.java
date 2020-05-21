package com.softeco.examples.services.order.model.event;

import com.softeco.examples.services.common.components.event.BaseEvent;
import com.softeco.examples.services.common.components.event.EventDomainType;
import com.softeco.examples.services.common.exception.MapException;
import com.softeco.examples.services.order.model.Order;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderCreateEvent extends BaseEvent<Order> {

    public OrderCreateEvent() {
        super();
    }

    public OrderCreateEvent(String commandId, Order payload) throws MapException {
        super(commandId, EventDomainType.ORDER, payload);
    }

}