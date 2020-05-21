package com.softeco.examples.services.catalog.controller.query;

import com.softeco.examples.services.catalog.model.Catalog;
import com.softeco.examples.services.catalog.repository.CatalogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/catalog/q")
@Profile({"CATALOG", "MONOLITH"})
public class CatalogQueryController {

	@Autowired
	private CatalogRepository repository;

	@GetMapping("/{id}")
	public Mono<Catalog> findById(@PathVariable("id") String id, ServerHttpRequest request) {
		log.info("findById: id={}", id);
		return repository.findById(id);
	}

	@GetMapping
	public Flux<Catalog> findAll() {
		log.info("findAll");
		return repository.findAll();
	}

	@GetMapping("/{id}/with-accounts")
	public Mono<Catalog> findByIdWithAccounts(@PathVariable("id") String id) {
		return Mono.empty();
	}

}