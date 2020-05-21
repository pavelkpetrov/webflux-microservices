package com.softeco.examples.services.aggregate.mapper;

import com.softeco.examples.services.account.model.Account;
import com.softeco.examples.services.aggregate.model.OrderAggregate;
import com.softeco.examples.services.common.components.clients.AccountClient;
import com.softeco.examples.services.common.components.clients.CustomerClient;
import com.softeco.examples.services.common.components.clients.OrderClient;
import com.softeco.examples.services.customer.model.Customer;
import com.softeco.examples.services.order.model.Order;
import lombok.Getter;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Getter
@Component
public class OrderIdAggregateMapper {

    @Autowired
    private OrderClient orderClient;
    @Autowired
    private CustomerClient customerClient;
    @Autowired
    private AccountClient accountClient;

    private OrderIdAggregateConverter orderIdAggregateConverter;

    public OrderIdAggregateMapper() {
        orderIdAggregateConverter =  new OrderIdAggregateConverter();
    }

    public class OrderIdAggregateConverter implements Converter<String, Mono<OrderAggregate>> {
        @Override
        public Mono<OrderAggregate> convert(MappingContext<String, Mono<OrderAggregate>> context) {
            Mono<Order> orderMono =  orderClient.findById(context.getSource());
            Mono<Customer> customerMono = Mono.from(orderMono)
                    .flatMap(order -> customerClient.findById(order.getCustomerId()));
            Mono<Account> accountMono = Mono.from(orderMono)
                    .flatMap(order -> accountClient.findById(order.getAccountId()));
            Mono<OrderAggregate> result =  Mono.from(orderMono).map(order -> new OrderAggregate(order))
                    .flatMap(root -> customerMono.map(customer -> {
                        root.setCustomerId(customer);
                        return root;
                    }))
                    .flatMap(root -> accountMono.map(account -> {
                        root.setAccountId(account);
                        return root;
                    }));
            return result;
        }
    }

}