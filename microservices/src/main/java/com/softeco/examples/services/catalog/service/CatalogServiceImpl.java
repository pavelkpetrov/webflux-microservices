package com.softeco.examples.services.catalog.service;

import com.softeco.examples.services.catalog.model.Catalog;
import com.softeco.examples.services.catalog.model.CatalogCreateCommand;
import com.softeco.examples.services.catalog.model.CatalogUpdateCommand;
import com.softeco.examples.services.catalog.model.event.CatalogCreateEvent;
import com.softeco.examples.services.catalog.model.event.CatalogUpdateEvent;
import com.softeco.examples.services.catalog.repository.CatalogRepository;
import com.softeco.examples.services.common.components.event.EventProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    private CatalogRepository repository;

    @Autowired
    private EventProvider eventProvider;

    public Mono<Catalog> create(CatalogCreateCommand catalogC) {
        log.info("create: {}", catalogC);
        try {
            return repository.save(catalogC.getPayload())
                    .map(newCatalog -> {
                        fireCatalogCreated(catalogC.getCqrsId(), newCatalog);
                        return newCatalog;
                    })
                    .onErrorResume(e -> {
                        log.error(e.getMessage(), e);
                        throw (RuntimeException)e;
                    });
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    public Mono<Catalog> update(CatalogUpdateCommand catalogC, String orderId) {
        log.info("create: {}", catalogC);
        try {
            return repository.save(catalogC.getPayload())
                    .map(newCatalog -> {
                        fireCatalogUpdated(catalogC.getCqrsId(), newCatalog, orderId);
                        return newCatalog;
                    })
                    .onErrorResume(e -> {
                        log.error(e.getMessage(), e);
                        throw (RuntimeException)e;
                    });
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    private void fireCatalogCreated(String cqrsId, Catalog catalog){
        CatalogCreateEvent event = new CatalogCreateEvent(cqrsId, catalog);
        eventProvider.sendEvent(event);
    }

    private void fireCatalogUpdated(String cqrsId, Catalog catalog, String orderId){
        CatalogUpdateEvent event = new CatalogUpdateEvent(cqrsId, catalog, orderId);
        eventProvider.sendEvent(event);
    }

}
