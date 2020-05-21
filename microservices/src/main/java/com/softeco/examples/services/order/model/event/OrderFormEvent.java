package com.softeco.examples.services.order.model.event;

import com.softeco.examples.services.common.components.event.BaseEvent;
import com.softeco.examples.services.common.components.event.EventDomainType;
import com.softeco.examples.services.common.exception.MapException;
import com.softeco.examples.services.order.model.Order;

public class OrderFormEvent extends BaseEvent<Order> {

    public OrderFormEvent() {
        super();
    }

    public OrderFormEvent(String commandId, Order payload) throws MapException {
        super(commandId, EventDomainType.ORDER, payload);
    }

}