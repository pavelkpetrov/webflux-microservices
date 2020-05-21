package com.softeco.examples.services.common.cqrs.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PayloadError extends PayloadBase {
    private String errorCode;
    private String errorMessage;
    private String errorMessageLocalized;

    public PayloadError(){
        payloadType = PayloadType.ERROR;
    }

}
