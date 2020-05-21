package com.softeco.examples.services.order.service;

import com.softeco.examples.services.order.model.Order;
import com.softeco.examples.services.order.model.OrderCreateCommand;
import com.softeco.examples.services.order.model.OrderFormCommand;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderService {

    Mono<Order> create(OrderCreateCommand orderC);
    Mono<Order> orderForm(OrderFormCommand orderC);

    Flux<Order> findByCustomer(String customerId);
    Flux<Order> findAll();
    Mono<Order> findById(@PathVariable("id") String id);
}
