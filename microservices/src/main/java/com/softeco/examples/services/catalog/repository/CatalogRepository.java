package com.softeco.examples.services.catalog.repository;

import com.softeco.examples.services.catalog.model.Catalog;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface CatalogRepository extends ReactiveCrudRepository<Catalog, String> {

	Flux<Catalog> findByName(String name);
	
}
