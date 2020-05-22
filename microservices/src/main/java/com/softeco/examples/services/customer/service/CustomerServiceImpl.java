package com.softeco.examples.services.customer.service;

import com.softeco.examples.services.account.model.Account;
import com.softeco.examples.services.common.components.clients.AccountClient;
import com.softeco.examples.services.common.components.event.EventProvider;
import com.softeco.examples.services.customer.model.Customer;
import com.softeco.examples.services.customer.model.CustomerCreateCommand;
import com.softeco.examples.services.common.event.CustomerCreateEvent;
import com.softeco.examples.services.customer.repository.CustomerRepository;
import com.softeco.examples.services.customer.utils.CustomerMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private EventProvider eventProvider;

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private AccountClient accountClient;

    public Mono<Customer> create(CustomerCreateCommand customerC) {
        log.info("create: {}", customerC);
        try {
            return repository.save(customerC.getPayload())
                    .map(newCatalog -> {
                        fireCustomerCreated(customerC.getCqrsId(), newCatalog);
                        return newCatalog;
                    })
                    .onErrorResume(e -> {
                        log.error(e.getMessage(), e);
                        throw (RuntimeException)e;
                    });
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    private void fireCustomerCreated(String cqrsId, Customer customer){
//        CustomerCreateEvent event = new CustomerCreateEvent(cqrsId, customer);
//        eventProvider.sendEvent(event);
    }

    public Mono<Customer> findById(String id) {
        log.info("findById: id={}", id);
        return repository.findById(id);
    }

    public Flux<Customer> findAll() {
        log.info("findAll");
        return repository.findAll();
    }

    public Mono<Customer> findByIdWithAccounts(String id) {
        log.info("findByIdWithAccounts: id={}", id);
        Flux<Account> accounts = accountClient.findByCustomer(id);
        return accounts
                .collectList()
                .map(a -> new Customer(Collections.enumeration(a)))
                .mergeWith(repository.findById(id))
                .collectList()
                .map(CustomerMapper::map);
    }

}
