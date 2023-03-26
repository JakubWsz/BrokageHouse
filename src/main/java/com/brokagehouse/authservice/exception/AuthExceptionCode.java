package com.brokagehouse.authservice.exception;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public enum AuthExceptionCode {

    INVALID_CREDENTIALS("Invalid credentials passed", 400);

    String message;
    int status;

    public String getMessage() {
        return message;
    }


    public int getStatus() {
        return status;
    }

    public AuthException createValidationException() {
        return new AuthException(this);
    }
}
