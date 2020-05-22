package com.softeco.examples.services.common.event;

import com.softeco.examples.services.catalog.model.Catalog;
import com.softeco.examples.services.common.components.event.BaseEvent;
import com.softeco.examples.services.common.components.event.EventDomainType;
import com.softeco.examples.services.common.exception.MapException;

public class CatalogCreateEvent  extends BaseEvent<Catalog> {

    public CatalogCreateEvent() {
        super();
    }

    public CatalogCreateEvent(String commandId, Catalog payload) throws MapException {
        super(commandId, EventDomainType.CATALOG, payload);
    }

    @Override
    public String toString(){
        return super.toString();
    }

}