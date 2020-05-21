package com.softeco.examples.services.customer.controller.command;

import com.softeco.examples.services.common.ResponseEntityUtils;
import com.softeco.examples.services.common.cqrs.model.CommandRes;
import com.softeco.examples.services.common.exception.CommandWrapException;
import com.softeco.examples.services.customer.model.Customer;
import com.softeco.examples.services.customer.model.CustomerCreateCommand;
import com.softeco.examples.services.customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/customer")
@Profile({"CUSTOMER", "MONOLITH"})
public class CustomerCommandController {

	@Autowired
	private CustomerService customerService;

	@PostMapping
	public Mono<ResponseEntity<CommandRes<Customer>>> create(@RequestBody CustomerCreateCommand customerC) {
		log.info("create: {}", customerC);
		try {
			return customerService.create(customerC)
					.map(catalog -> ResponseEntityUtils.commandSuccess(catalog, customerC.getCqrsId()))
					.onErrorResume(e -> {
						log.error(e.getMessage(), e);
						throw new CommandWrapException(customerC, e.getMessage(), e);
					});
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new CommandWrapException(customerC, ex.getMessage(), ex);
		}
	}
	
}
