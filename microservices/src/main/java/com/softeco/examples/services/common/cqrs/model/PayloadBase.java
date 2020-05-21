package com.softeco.examples.services.common.cqrs.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PayloadBase<T> {

    PayloadType payloadType;

}
