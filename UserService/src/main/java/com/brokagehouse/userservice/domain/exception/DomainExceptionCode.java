package com.brokagehouse.userservice.domain.exception;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public enum DomainExceptionCode {
    EMAIL_ALREADY_EXISTS("Passed email already exists", 400),
    PERSONAL_ID_NUMBER_ALREADY_EXISTS("Passed personal ID number already exists", 400);

    String message;
    int status;

    public String getMessage() {
        return message;
    }


    public int getStatus() {
        return status;
    }

    public ValidationException createValidationException() {
        return new ValidationException(this);
    }
}
