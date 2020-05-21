package com.softeco.examples.services.catalog.service;

import com.softeco.examples.services.catalog.model.Catalog;
import com.softeco.examples.services.catalog.model.CatalogCreateCommand;
import com.softeco.examples.services.catalog.model.CatalogUpdateCommand;
import reactor.core.publisher.Mono;

public interface CatalogService {

    Mono<Catalog> create(CatalogCreateCommand catalogC);
    Mono<Catalog> update(CatalogUpdateCommand catalogC, String orderId);

}
