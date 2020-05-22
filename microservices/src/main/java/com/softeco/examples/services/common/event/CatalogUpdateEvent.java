package com.softeco.examples.services.common.event;

import com.softeco.examples.services.catalog.model.Catalog;
import com.softeco.examples.services.common.components.event.BaseEvent;
import com.softeco.examples.services.common.components.event.EventDomainType;
import com.softeco.examples.services.common.exception.MapException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CatalogUpdateEvent extends BaseEvent<Catalog> {

    private String orderId;

    public CatalogUpdateEvent() {
        super();
    }

    public CatalogUpdateEvent(String commandId, Catalog payload, String orderId) throws MapException {
        super(commandId, EventDomainType.CATALOG, payload);
        this.orderId = orderId;
    }

}