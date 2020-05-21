package com.softeco.examples.services.customer.model.event;

import com.softeco.examples.services.common.components.event.BaseEvent;
import com.softeco.examples.services.common.components.event.EventDomainType;
import com.softeco.examples.services.common.exception.MapException;
import com.softeco.examples.services.customer.model.Customer;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerCreateEvent extends BaseEvent<Customer> {

    public CustomerCreateEvent() {
        super();
    }

    public CustomerCreateEvent(String commandId, Customer payload) throws MapException {
        super(commandId, EventDomainType.CUSTOMER, payload);
    }

}
