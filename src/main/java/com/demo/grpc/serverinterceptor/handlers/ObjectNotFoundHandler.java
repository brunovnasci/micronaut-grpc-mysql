package com.demo.grpc.serverinterceptor.handlers;

import com.demo.gateway.mysql.exceptions.ObjectNotFoundException;
import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.Status;

import javax.inject.Singleton;

@Singleton
public class ObjectNotFoundHandler implements ExceptionHandler {
    @Override
    public boolean supports(Exception exception) {
        return exception instanceof ObjectNotFoundException;
    }

    @Override
    public void handle(Exception exception, ServerCall<?, ?> serverCall, Metadata metadata) {
        serverCall.close(Status.NOT_FOUND.withDescription("Person not found"), metadata);
    }
}
