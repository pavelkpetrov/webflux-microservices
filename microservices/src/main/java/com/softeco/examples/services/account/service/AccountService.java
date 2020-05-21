package com.softeco.examples.services.account.service;

import com.softeco.examples.services.account.model.Account;
import com.softeco.examples.services.account.model.AccountCreateCommand;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountService {

    Mono<Account> create(AccountCreateCommand accountC);
    Flux<Account> findByCustomer(String customerId);
    Flux<Account> findAll();
    Mono<Account> findById(String id);

}
