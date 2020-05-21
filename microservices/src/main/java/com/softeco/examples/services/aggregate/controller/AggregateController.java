package com.softeco.examples.services.aggregate.controller;

import com.softeco.examples.services.aggregate.model.OrderAggregate;
import com.softeco.examples.services.aggregate.service.OrderAggregateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/aggregate")
@Profile({"AGGREGATE", "MONOLITH"})
public class AggregateController {

    @Autowired
    private OrderAggregateService orderAggregateService;

    @GetMapping("/order/{id}")
    public Mono<OrderAggregate> getOrderById(@PathVariable("id") String id) {
        log.info("getOrderById: id={}", id);
        return orderAggregateService.getOrderById(id);
    }

}
