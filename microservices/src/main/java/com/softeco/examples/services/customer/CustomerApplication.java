package com.softeco.examples.services.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.reactive.function.client.WebClient;
import reactivefeign.spring.config.EnableReactiveFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@Profile("CUSTOMER")
@EnableReactiveFeignClients(basePackages = {"com.softeco.examples.services.common.components.clients"})
@EnableFeignClients(basePackages = {"com.softeco.examples.services.common.components.clients"})
@ComponentScan(basePackages = {"com.softeco.examples.services.customer", "com.softeco.examples.services.common.components"})
@PropertySource({"classpath:application.micro.properties"})
public class CustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public WebClient.Builder loadBalancedWebClientBuilder() {
		return WebClient.builder();
	}
	
}
