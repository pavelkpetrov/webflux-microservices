package com.softeco.examples.services.common.cqrs.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommandRes<T> {
    private String cqrsId;
    private OperationType operationType;
    PayloadType payloadType;
    PayloadBase<T> commandPayload;
}
