package com.softeco.examples.services.account.controller.query;

import com.softeco.examples.services.account.model.Account;
import com.softeco.examples.services.account.service.AccountService;
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
@RequestMapping("/account/q")
@Profile({"ACCOUNT", "MONOLITH"})
@Slf4j
public class AccountQueryController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/customer/{customer}")
    public Flux<Account> findByCustomer(@PathVariable("customer") String customerId) {
        log.info("findByCustomer: customerId={}", customerId);
        return accountService.findByCustomer(customerId);
    }

    @GetMapping
    public Flux<Account> findAll() {
        log.info("findAll");
        return accountService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Account> findById(@PathVariable("id") String id) {
        log.info("findById: id={}", id);
        return accountService.findById(id);
    }

}
