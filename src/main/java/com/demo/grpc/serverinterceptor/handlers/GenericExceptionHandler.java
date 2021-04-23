package com.demo.grpc.serverinterceptor.handlers;

import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.Status;

import javax.inject.Singleton;

@Singleton
public class GenericExceptionHandler implements ExceptionHandler {
    @Override
    public boolean supports(Exception exception) {
        return false;
    }

    @Override
    public void handle(Exception exception, ServerCall<?, ?> serverCall, Metadata metadata) {
        serverCall.close(Status.INTERNAL, metadata);
    }
}
