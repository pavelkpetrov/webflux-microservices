package com.softeco.examples.services.common.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import reactor.core.publisher.Mono;

public interface MonoValidator<T> extends Validator {

    Mono<Errors> validateMono(T target, Errors errors);

}
