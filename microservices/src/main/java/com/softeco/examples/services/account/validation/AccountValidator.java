package com.softeco.examples.services.account.validation;

import com.softeco.examples.services.account.model.AccountCreateCommand;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class AccountValidator implements ConstraintValidator<ValidAccountRq, AccountCreateCommand> {

    @Override
    public boolean isValid(AccountCreateCommand accountC, ConstraintValidatorContext context) {
        if (StringUtils.isBlank(accountC.getPayload().getName())) {
            return false;
        }
        return true;
    }

}

