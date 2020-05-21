package com.softeco.examples.services.common.cqrs.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PayloadCommand<T> extends PayloadBase<T> {

    private T data;

    public PayloadCommand(){
        payloadType = PayloadType.DATA;
    }

}
