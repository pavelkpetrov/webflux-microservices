package com.softeco.examples.services.common.validator;

import com.softeco.examples.services.common.cqrs.command.Command;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import reactor.core.publisher.Mono;

@Slf4j
public class ValidUtils {

    public static <T> Mono<Errors> validateWithReactiveValidator(Command<T> object, MonoValidator<Command<T>> validator,
                                                                 boolean throwError){
        Errors errors = new BeanPropertyBindingResult(
                object,
                object.getClass().getName());
        if (validator.supports(object.getClass())) {
            return validator.validateMono(object, errors)
                    .flatMap(errs ->
                    {
                        if (throwError && errs.hasErrors() || errs.hasFieldErrors() || errs.hasGlobalErrors()) {
                            log.error("Validation for validator class {} has following errors: {}", validator.getClass(), errs);
                            throw new ReactiveBindException("Reactive validation error", (BindingResult)errs);
                        }
                        return Mono.just(errs);
                    });
        } else {
            return Mono.just(errors);
        }
    }

}
