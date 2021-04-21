package com.demo.grpc.exceptions;

public class RequiredFieldException extends RuntimeException {
    public RequiredFieldException() {
    }

    public RequiredFieldException(String message) {
        super(message);
    }

    public RequiredFieldException(String message, Throwable cause) {
        super(message, cause);
    }
}
