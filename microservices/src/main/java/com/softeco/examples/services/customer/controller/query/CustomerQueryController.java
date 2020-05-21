package com.softeco.examples.services.customer.controller.query;

import com.softeco.examples.services.customer.model.Customer;
import com.softeco.examples.services.customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/customer/q")
@Profile({"CUSTOMER", "MONOLITH"})
public class CustomerQueryController {

	@Autowired
	private CustomerService customerService;

	@GetMapping("/{id}")
	public Mono<Customer> findById(@PathVariable("id") String id) {
		log.info("findById: id={}", id);
		return customerService.findById(id);
	}

	@GetMapping
	public Flux<Customer> findAll() {
		log.info("findAll");
		return customerService.findAll();
	}

	@GetMapping("/{id}/with-accounts")
	public Mono<Customer> findByIdWithAccounts(@PathVariable("id") String id) {
		log.info("findByIdWithAccounts: id={}", id);
		return customerService.findByIdWithAccounts(id);
	}

}
