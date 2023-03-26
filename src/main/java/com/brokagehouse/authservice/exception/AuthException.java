package com.brokagehouse.authservice.exception;

public class AuthException extends RuntimeException{
    AuthExceptionCode code;

    public AuthException(AuthExceptionCode code, Object... arguments) {
        super(String.format(code.getMessage(), arguments));
        this.code = code;
    }

    public AuthExceptionCode getCode() {
        return code;
    }
}
