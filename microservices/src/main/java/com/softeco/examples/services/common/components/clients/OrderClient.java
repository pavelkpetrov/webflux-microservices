package com.softeco.examples.services.common.components.clients;

import com.softeco.examples.services.order.model.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(name="order-service", url="${service.url.order}")
public interface OrderClient {

    @GetMapping("/order/q/{id}")
    Mono<Order> findById(@PathVariable("id") String id);

}
