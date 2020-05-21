package com.softeco.examples.services.common.components;

import com.softeco.examples.services.common.cqrs.model.CommandRes;
import com.softeco.examples.services.common.cqrs.model.PayloadError;
import com.softeco.examples.services.common.validator.ReactiveBindException;
import com.softeco.examples.services.common.validator.ValidationAdvice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class ControllerExceptionAdvice extends ValidationAdvice {

    @Override
    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<CommandRes<PayloadError>> validationError(WebExchangeBindException ex) {
        return super.validationError(ex);
    }

    @Override
    @ExceptionHandler(ReactiveBindException.class)
    public ResponseEntity<CommandRes<PayloadError>> validationError(ReactiveBindException ex) {
        return super.validationError(ex);
    }

    @Override
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<CommandRes<PayloadError>> serverError(Throwable exm) {
        return super.serverError(exm);
    }

}
