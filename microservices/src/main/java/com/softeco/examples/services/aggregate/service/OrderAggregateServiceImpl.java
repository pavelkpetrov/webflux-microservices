package com.softeco.examples.services.aggregate.service;

import com.softeco.examples.services.aggregate.model.OrderAggregate;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class OrderAggregateServiceImpl implements OrderAggregateService {

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/order/{id}")
    public Mono<OrderAggregate> getOrderById(@PathVariable("id") String id) {
        log.info("getOrderById: id={}", id);
        return modelMapper.map(id,  new TypeToken<Mono<OrderAggregate>>(){}.getType());
    }

}
