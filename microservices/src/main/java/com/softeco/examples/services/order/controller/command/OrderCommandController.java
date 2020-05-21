package com.softeco.examples.services.order.controller.command;

import com.softeco.examples.services.common.ResponseEntityUtils;
import com.softeco.examples.services.common.cqrs.model.CommandRes;
import com.softeco.examples.services.common.exception.CommandWrapException;
import com.softeco.examples.services.order.model.Order;
import com.softeco.examples.services.order.model.OrderCreateCommand;
import com.softeco.examples.services.order.model.OrderFormCommand;
import com.softeco.examples.services.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/order/c")
@Profile({"ORDER", "MONOLITH"})
@Slf4j
public class OrderCommandController {

	@Autowired
	private OrderService orderService;

	@PostMapping
	public Mono<ResponseEntity<CommandRes<Order>>> create(@RequestBody OrderCreateCommand orderC) {
		log.info("create: {}", orderC);
		try {
			return orderService.create(orderC)
					.map(order -> ResponseEntityUtils.commandSuccess(order, orderC.getCqrsId()))
					.onErrorResume(e -> {
						log.error(e.getMessage(), e);
						throw new CommandWrapException(orderC, e.getMessage(), e);
					});
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new CommandWrapException(orderC, ex.getMessage(), ex);
		}
	}

	@PutMapping("/form")
	public Mono<ResponseEntity<CommandRes<Order>>> orderForm(@RequestBody OrderFormCommand orderC) {
		log.info("form: {}", orderC);
		try {
			return orderService.orderForm(orderC)
					.map(order -> ResponseEntityUtils.commandSuccess(order, orderC.getCqrsId()))
					.onErrorResume(e -> {
						log.error(e.getMessage(), e);
						throw new CommandWrapException(orderC, e.getMessage(), e);
					});
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new CommandWrapException(orderC, ex.getMessage(), ex);
		}
	}

}
