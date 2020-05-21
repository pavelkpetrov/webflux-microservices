package com.softeco.examples.services.account.model.event;

import com.softeco.examples.services.account.model.Account;
import com.softeco.examples.services.common.components.event.BaseEvent;
import com.softeco.examples.services.common.components.event.EventDomainType;
import com.softeco.examples.services.common.exception.MapException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AccountCreateEvent extends BaseEvent<Account> {

    public AccountCreateEvent() {
        super();
    }

    public AccountCreateEvent(String commandId, Account payload) throws MapException {
        super(commandId, EventDomainType.ACCOUNT, payload);
    }

}