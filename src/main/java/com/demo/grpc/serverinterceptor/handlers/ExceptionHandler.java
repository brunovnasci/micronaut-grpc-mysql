package com.demo.grpc.serverinterceptor.handlers;

import io.grpc.Metadata;
import io.grpc.ServerCall;

public interface ExceptionHandler {

    boolean supports(Exception exception);

    void handle(Exception exception, ServerCall<?, ?> serverCall, Metadata metadata);
}
