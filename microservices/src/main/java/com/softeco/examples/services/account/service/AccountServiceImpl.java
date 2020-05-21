package com.softeco.examples.services.account.service;

import com.softeco.examples.services.account.model.Account;
import com.softeco.examples.services.account.model.AccountCreateCommand;
import com.softeco.examples.services.account.model.event.AccountCreateEvent;
import com.softeco.examples.services.account.repository.AccountRepository;
import com.softeco.examples.services.common.components.event.EventProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository repository;

    @Autowired
    private EventProvider eventProvider;

    public Mono<Account> create(AccountCreateCommand accountC) { //implements annotation validation
        log.info("create: {}", accountC);
        try {
            return repository.save(accountC.getPayload())
                    .map(newAccount -> {
                        fireAccountCreated(accountC.getCqrsId(), newAccount);
                        return newAccount;
                    });
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    private void fireAccountCreated(String cqrsId, Account account){
        AccountCreateEvent event = new AccountCreateEvent(cqrsId, account);
        eventProvider.sendEvent(event);
    }

    public Flux<Account> findByCustomer(String customerId) {
        log.info("findByCustomer: customerId={}", customerId);
        return repository.findByCustomerId(customerId);
    }

    public Flux<Account> findAll() {
        log.info("findAll");
        return repository.findAll();
    }

    public Mono<Account> findById(String id) {
        log.info("findById: id={}", id);
        return repository.findById(id);
    }

}
