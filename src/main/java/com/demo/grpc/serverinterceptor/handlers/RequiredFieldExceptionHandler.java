package com.demo.grpc.serverinterceptor.handlers;

import com.demo.grpc.exceptions.RequiredFieldException;
import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.Status;

import javax.inject.Singleton;

@Singleton
public class RequiredFieldExceptionHandler implements ExceptionHandler {
    @Override
    public boolean supports(Exception exception) {
        return exception instanceof RequiredFieldException;
    }

    @Override
    public void handle(Exception exception, ServerCall<?, ?> serverCall, Metadata metadata) {
        serverCall.close(Status.INVALID_ARGUMENT.withDescription(exception.getMessage()), metadata);
    }
}
