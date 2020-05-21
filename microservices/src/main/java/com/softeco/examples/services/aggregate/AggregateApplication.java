package com.softeco.examples.services.aggregate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import reactivefeign.spring.config.EnableReactiveFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@Profile("AGGREGATE")
@EnableReactiveFeignClients(basePackages = {"com.softeco.examples.services.common.components.clients"})
@EnableFeignClients(basePackages = {"com.softeco.examples.services.common.components.clients"})
@ComponentScan(basePackages = {"com.softeco.examples.services.aggregate", "com.softeco.examples.services.common.components"})
@PropertySource(value = {"classpath:application.micro.properties"})
public class AggregateApplication {

    public static void main(String[] args) {
        SpringApplication.run(AggregateApplication.class, args);
    }

}
