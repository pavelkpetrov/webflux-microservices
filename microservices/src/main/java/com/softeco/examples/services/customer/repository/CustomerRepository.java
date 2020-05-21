package com.softeco.examples.services.customer.repository;

import com.softeco.examples.services.customer.model.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;


public interface CustomerRepository extends ReactiveCrudRepository<Customer, String> {
	
}
