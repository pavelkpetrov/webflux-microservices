package com.softeco.examples.services.order.controller.query;

import com.softeco.examples.services.order.model.Order;
import com.softeco.examples.services.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/order/q")
@Profile({"ORDER", "MONOLITH"})
@Slf4j
public class OrderQueryController {

	@Autowired
	private OrderService orderService;

	@GetMapping("/order/{customer}")
	public Flux<Order> findByCustomer(@PathVariable("customer") String customerId) {
		log.info("findByCustomer: customerId={}", customerId);
		return orderService.findByCustomer(customerId);
	}

	@GetMapping
	public Flux<Order> findAll() {
		log.info("findAll");
		return orderService.findAll();
	}

	@GetMapping("/{id}")
	public Mono<Order> findById(@PathVariable("id") String id) {
		log.info("findById: id={}", id);
		return orderService.findById(id);
	}

}
