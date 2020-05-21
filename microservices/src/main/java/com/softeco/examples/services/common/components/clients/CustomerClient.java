package com.softeco.examples.services.common.components.clients;

import com.softeco.examples.services.customer.model.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(name="customer-service", url="${service.url.customer}")
public interface CustomerClient {

    @GetMapping("/customer/q/{id}")
    Mono<Customer> findById(@PathVariable("id") String id);

}
