package com.softeco.examples.services.aggregate.model;

import com.softeco.examples.services.account.model.Account;
import com.softeco.examples.services.customer.model.Customer;
import com.softeco.examples.services.order.model.Order;
import com.softeco.examples.services.order.model.OrderItem;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter
@Setter
@ToString
public class OrderAggregate {
    @Id
    private String id;
    private String number;
    private Customer customerId;
    private Account accountId;
    private List<OrderItem> orderItems;

    public OrderAggregate(Order order) {
        this.id = order.getId();
        this.number = order.getNumber();
        this.orderItems = order.getOrderItems();
    }

}
