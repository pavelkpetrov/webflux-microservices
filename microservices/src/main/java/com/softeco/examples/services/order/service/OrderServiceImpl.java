package com.softeco.examples.services.order.service;

import com.softeco.examples.services.common.components.event.EventProvider;
import com.softeco.examples.services.order.model.Order;
import com.softeco.examples.services.order.model.OrderCreateCommand;
import com.softeco.examples.services.order.model.OrderFormCommand;
import com.softeco.examples.services.order.model.OrderState;
import com.softeco.examples.services.common.event.OrderCreateEvent;
import com.softeco.examples.services.common.event.OrderFormEvent;
import com.softeco.examples.services.order.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService{

    @Autowired
    private EventProvider eventProvider;

    @Autowired
    private OrderRepository repository;

    public Mono<Order> create(OrderCreateCommand orderC) {
        log.info("create: {}", orderC);
        try {
            orderC.getPayload().setState(OrderState.CREATED);
            return repository.save(orderC.getPayload())
                    .map(newOrder -> {
                        fireOrderCreated(orderC.getCqrsId(), newOrder);
                        return newOrder;
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

    public Mono<Order> orderForm(OrderFormCommand orderC) {
        log.info("form: {}", orderC);
        try {
            return repository.findById(orderC.getPayload())
                    .map(order -> {
                        fireOrderFormed(orderC.getCqrsId(), order);
                        return order;
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

    private void fireOrderCreated(String cqrsId, Order order){
//        OrderCreateEvent event = new OrderCreateEvent(cqrsId, order);
//        eventProvider.sendEvent(event);
    }

    private void fireOrderFormed(String cqrsId, Order order){
        OrderFormEvent event = new OrderFormEvent(cqrsId, order);
        eventProvider.sendEvent(event);
    }

    public Flux<Order> findByCustomer(@PathVariable("customer") String customerId) {
        log.info("findByCustomer: customerId={}", customerId);
        return repository.findByCustomerId(customerId);
    }

    public Flux<Order> findAll() {
        log.info("findAll");
        return repository.findAll();
    }

    public Mono<Order> findById(@PathVariable("id") String id) {
        log.info("findById: id={}", id);
        return repository.findById(id);
    }

}
