package com.softeco.examples.services.common;

import com.softeco.examples.services.common.cqrs.command.Command;
import com.softeco.examples.services.common.cqrs.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResponseEntityUtils {

    public static <T> ResponseEntity<CommandRes<T>> commandSuccess(T data, String cqrsId){
        CommandRes<T> resultRes = new CommandRes<>();
        resultRes.setCqrsId(cqrsId);
        resultRes.setOperationType(OperationType.COMMAND);
        resultRes.setPayloadType(PayloadType.DATA);
        PayloadCommand<T> payload = new PayloadCommand<>();
        payload.setData(data);
        resultRes.setPayloadType(PayloadType.DATA);
        resultRes.setCommandPayload(payload);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(resultRes);
    }

    public static ResponseEntity<CommandRes<PayloadError>> commandServerError(String cqrsId, Throwable error){
        CommandRes<PayloadError> cResult = new CommandRes<>();
        cResult.setCqrsId(cqrsId);
        cResult.setOperationType(OperationType.COMMAND);
        cResult.setPayloadType(PayloadType.ERROR);
        PayloadError payload = new PayloadError();
        payload.setPayloadType(PayloadType.ERROR);
        payload.setErrorCode(Errors.SERVER_ERROR.getCode());
        payload.setErrorMessage(Errors.SERVER_ERROR.getMessage() + ". Details:" + error.getMessage());
        cResult.setCommandPayload(payload);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(cResult);
    }

    public static ResponseEntity<CommandRes<PayloadError>> commandValidationError(BindingResult result, Throwable error) {
        ObjectError[] errors;
        final List<ObjectError> objectErrors = new ArrayList<>();
        if (result.hasFieldErrors()) {
            objectErrors.addAll(result.getFieldErrors());
        }
        if (result.hasGlobalErrors()) {
            objectErrors.addAll(result.getGlobalErrors());
        }
        errors = objectErrors.toArray(new ObjectError[objectErrors.size()]);
        CommandRes<PayloadError> cResult = new CommandRes<>();
        if (result.getTarget() instanceof Command) {
            cResult.setCqrsId(((Command<?>)result.getTarget()).getCqrsId());
        }
        cResult.setOperationType(OperationType.COMMAND);
        cResult.setPayloadType(PayloadType.ERROR);
        PayloadError payload = new PayloadError();
        payload.setPayloadType(PayloadType.ERROR);
        payload.setErrorCode(Errors.VALIDATION_ERROR.getCode());
        payload.setErrorMessage(Errors.VALIDATION_ERROR.getMessage() + ". Details:" + Arrays.toString(errors));
        cResult.setCommandPayload(payload);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(cResult);
    }

}
