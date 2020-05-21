package com.softeco.examples.services.order.repository;

import com.softeco.examples.services.order.model.Order;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface OrderRepository extends ReactiveCrudRepository<Order, String> {

	Flux<Order> findByCustomerId(String customerId);
	
}
