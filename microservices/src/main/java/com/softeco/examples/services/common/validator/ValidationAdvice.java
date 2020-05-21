package com.softeco.examples.services.common.validator;

import com.softeco.examples.services.common.ResponseEntityUtils;
import com.softeco.examples.services.common.cqrs.model.CommandRes;
import com.softeco.examples.services.common.cqrs.model.PayloadError;
import com.softeco.examples.services.common.exception.CommandWrapException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.support.WebExchangeBindException;

@Slf4j
public abstract class ValidationAdvice {

    protected ResponseEntity<CommandRes<PayloadError>> validationError(WebExchangeBindException ex) {
        return validationError(ex.getBindingResult(), ex);
    }

    protected ResponseEntity<CommandRes<PayloadError>> validationError(ReactiveBindException ex) {
        return validationError(ex.getBindingResult(), ex);
    }

    protected ResponseEntity<CommandRes<PayloadError>> validationError(BindingResult result, Throwable error) {
        log.error(error.getMessage(), error);
        return ResponseEntityUtils.commandValidationError(result, error);
    }

    protected ResponseEntity<CommandRes<PayloadError>> serverError(Throwable exm) {
        if (exm instanceof CommandWrapException) {
            CommandWrapException ce = (CommandWrapException)exm;
            Throwable causeE = ce.getCause();
            if (causeE instanceof WebExchangeBindException) {
                return validationError((WebExchangeBindException)causeE);
            }
            if (causeE instanceof ReactiveBindException) {
                return validationError((ReactiveBindException)causeE);
            }
            return ResponseEntityUtils.commandServerError(ce.getCommand().getCqrsId(), ce.getCause());
        }
        return ResponseEntityUtils.commandServerError(null, exm);
    }

}
