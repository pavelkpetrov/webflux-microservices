package com.softeco.examples.services.aggregate.service;

import com.softeco.examples.services.aggregate.model.OrderAggregate;
import reactor.core.publisher.Mono;

public interface OrderAggregateService {

    Mono<OrderAggregate> getOrderById(String id);

}
