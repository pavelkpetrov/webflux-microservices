package com.softeco.examples.services.customer.service;

import com.softeco.examples.services.customer.model.Customer;
import com.softeco.examples.services.customer.model.CustomerCreateCommand;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {

    Mono<Customer> create(CustomerCreateCommand customerC);
    Mono<Customer> findById(@PathVariable("id") String id);
    Flux<Customer> findAll();
    Mono<Customer> findByIdWithAccounts(@PathVariable("id") String id);
}
