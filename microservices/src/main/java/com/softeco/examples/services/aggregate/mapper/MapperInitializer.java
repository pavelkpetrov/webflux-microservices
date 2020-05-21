package com.softeco.examples.services.aggregate.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MapperInitializer {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private OrderIdAggregateMapper orderIdAggregateRootMapper;

    @PostConstruct
    public void init() {
        modelMapper.addConverter(orderIdAggregateRootMapper.getOrderIdAggregateConverter());
    }

}
