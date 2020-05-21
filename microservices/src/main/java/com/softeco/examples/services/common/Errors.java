package com.softeco.examples.services.common;

import lombok.Getter;

@Getter
public enum Errors {

    VALIDATION_ERROR("01", "Validation Error"),
    SERVER_ERROR("02", "Server Error");

    private String code;
    private String message;

    Errors(String code, String message) {
        this.code = code;
        this.message = message;
    }


}
