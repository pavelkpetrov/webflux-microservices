package com.softeco.examples.services.common.components.clients;

import com.softeco.examples.services.account.model.Account;
import com.softeco.examples.services.common.cqrs.command.Command;
import com.softeco.examples.services.common.cqrs.model.CommandRes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(name="account-service", url="${service.url.account}")
public interface AccountClient {

    @GetMapping("/account/q/{id}")
    Mono<Account> findById(@PathVariable("id") String id);

    @GetMapping("/account/q/customer/{customer}")
    Flux<Account> findByCustomer(@PathVariable("customer") String customerId);

    @PostMapping("/account/c")
    Mono<ResponseEntity<CommandRes<Account>>> create(@RequestBody Command<Account> accountC);

}
