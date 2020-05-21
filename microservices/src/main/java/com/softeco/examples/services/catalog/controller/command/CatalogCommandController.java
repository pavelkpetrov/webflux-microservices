package com.softeco.examples.services.catalog.controller.command;

import com.softeco.examples.services.catalog.model.Catalog;
import com.softeco.examples.services.catalog.model.CatalogCreateCommand;
import com.softeco.examples.services.catalog.model.CatalogUpdateCommand;
import com.softeco.examples.services.catalog.service.CatalogService;
import com.softeco.examples.services.common.ResponseEntityUtils;
import com.softeco.examples.services.common.cqrs.model.CommandRes;
import com.softeco.examples.services.common.exception.CommandWrapException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/catalog/c")
@Profile({"CATALOG", "MONOLITH"})
public class CatalogCommandController {

	@Autowired
	private CatalogService catalogService;

	@PostMapping
	public Mono<ResponseEntity<CommandRes<Catalog>>> create(@RequestBody CatalogCreateCommand catalogC) {
		log.info("create: {}", catalogC);
		try {
			return catalogService.create(catalogC)
					.map(catalog -> ResponseEntityUtils.commandSuccess(catalog, catalogC.getCqrsId()))
					.onErrorResume(e -> {
						log.error(e.getMessage(), e);
						throw new CommandWrapException(catalogC, e.getMessage(), e);
					});
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new CommandWrapException(catalogC, ex.getMessage(), ex);
		}
	}

	@PutMapping("/{orderId}")
	public Mono<ResponseEntity<CommandRes<Catalog>>> update(@RequestBody CatalogUpdateCommand catalogC, @PathVariable("orderId") String orderId) {
		log.info("create: {}", catalogC);
		try {
			return catalogService.update(catalogC, orderId)
					.map(catalog -> ResponseEntityUtils.commandSuccess(catalog, catalogC.getCqrsId()))
					.onErrorResume(e -> {
						log.error(e.getMessage(), e);
						throw new CommandWrapException(catalogC, e.getMessage(), e);
					});
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new CommandWrapException(catalogC, ex.getMessage(), ex);
		}
	}

}