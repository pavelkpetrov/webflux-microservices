package com.softeco.examples.services.account.controller.command;

import com.softeco.examples.services.account.model.Account;
import com.softeco.examples.services.account.model.AccountCreateCommand;
import com.softeco.examples.services.account.service.AccountService;
import com.softeco.examples.services.account.validation.AccountReactiveValidator;
import com.softeco.examples.services.common.ResponseEntityUtils;
import com.softeco.examples.services.common.cqrs.model.CommandRes;
import com.softeco.examples.services.common.exception.CommandWrapException;
import com.softeco.examples.services.common.validator.ValidUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/account/c")
@Profile({"ACCOUNT", "MONOLITH"})
@Slf4j
public class AccountCommandController {

    @Autowired
    private AccountReactiveValidator validator;

    @Autowired
    private AccountService accountService;

    @PostMapping
//    public Mono<ResponseEntity> create(@RequestBody @Valid AccountCommand accountC) { //implements annotation validation
    public Mono<ResponseEntity<CommandRes<Account>>> create(@RequestBody @Validated AccountCreateCommand accountC) { //implements annotation validation
        log.info("create: {}", accountC);
        try {
            Mono<Errors> errors = ValidUtils.validateWithReactiveValidator(accountC, validator, true);
            return Mono.from(errors)
                .flatMap(err -> accountService.create(accountC))
                .map(account -> ResponseEntityUtils.commandSuccess(account, accountC.getCqrsId()))
                .onErrorResume(e -> {
                    log.error(e.getMessage(), e);
                    throw new CommandWrapException(accountC, e.getMessage(), e);
                });
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw new CommandWrapException(accountC, ex.getMessage(), ex);
        }
    }

}
