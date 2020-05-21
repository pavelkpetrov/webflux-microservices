package com.softeco.examples.services.catalog.model;

import com.softeco.examples.services.account.validation.ValidAccountRq;
import com.softeco.examples.services.common.cqrs.command.Command;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ValidAccountRq
public class CatalogCreateCommand extends Command<Catalog> {

}

