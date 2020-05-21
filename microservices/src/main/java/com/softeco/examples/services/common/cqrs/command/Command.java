package com.softeco.examples.services.common.cqrs.command;

import com.softeco.examples.services.common.cqrs.model.CommandReq;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;

@Getter
@Setter
@ToString
public class Command<In> extends CommandReq<In> {

    @Valid
    private In payload;

}
