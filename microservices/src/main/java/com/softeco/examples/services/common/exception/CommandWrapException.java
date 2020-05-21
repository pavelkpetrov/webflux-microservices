package com.softeco.examples.services.common.exception;

import com.softeco.examples.services.common.cqrs.command.Command;
import lombok.Getter;

@Getter
public class CommandWrapException extends RuntimeException {

    private Command command;

    public CommandWrapException(Command command, String message, Throwable ex) {
        super(message, ex);
        this.command = command;
    }

}
