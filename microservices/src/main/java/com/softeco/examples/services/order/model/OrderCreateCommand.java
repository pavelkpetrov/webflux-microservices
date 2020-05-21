package com.softeco.examples.services.order.model;

import com.softeco.examples.services.common.cqrs.command.Command;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderCreateCommand extends Command<Order>{

}
