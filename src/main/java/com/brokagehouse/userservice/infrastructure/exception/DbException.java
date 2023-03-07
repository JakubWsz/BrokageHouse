package com.brokagehouse.userservice.infrastructure.exception;

public class DbException extends RuntimeException{
    DbExceptionCode code;

    public DbException(DbExceptionCode code, Object... arguments) {
        super(String.format(code.getMessage(), arguments));
        this.code = code;
    }

    public DbExceptionCode getCode() {
        return code;
    }
}
