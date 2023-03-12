package com.brokagehouse.userservice.domain.exception;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ValidationException extends RuntimeException{
    DomainExceptionCode code;

    public ValidationException(DomainExceptionCode code, Object... arguments) {
        super(String.format(code.getMessage(), arguments));
        this.code = code;
    }

    public DomainExceptionCode getCode() {
        return code;
    }
}
