package com.softeco.examples.services.order.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@ToString
@Document
public class Order {

	@Id
	private String id;
	private String number;
	private String customerId;
	private String accountId;
	private List<OrderItem> orderItems;
	private OrderState state = OrderState.CREATED;

	public Order() {

	}
	
}
