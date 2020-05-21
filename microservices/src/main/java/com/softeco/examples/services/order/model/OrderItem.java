package com.softeco.examples.services.order.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderItem {

    private String catalogId;
    private int amount;


}
