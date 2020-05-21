package com.softeco.examples.services.account.validation;

import com.softeco.examples.services.account.model.Account;
import com.softeco.examples.services.account.repository.AccountRepository;
import com.softeco.examples.services.common.cqrs.command.Command;
import com.softeco.examples.services.common.validator.ReactiveValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import reactor.core.publisher.Mono;

@Component
public class AccountReactiveValidator extends ReactiveValidator<Command<Account>> {

    @Autowired
    private AccountRepository repository;

    @Override
    public boolean supports(Class<?> clazz) {
        return Command.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
    }

    @Override
    public Mono<Errors> validateMono(Command<Account> target, Errors errors) {
        Mono<Boolean> exists = repository.existsAccountByName(target.getPayload().getName());
        return exists.flatMap(exist -> {
            if (exist) {
                errors.rejectValue("payload.name", "", "Payload account name is duplicated");
            }
            return Mono.just(errors);
        });
    }

}
