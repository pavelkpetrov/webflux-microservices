package com.softeco.examples.services.common.cqrs.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommandReq<In> {

    private String cqrsId;

}
