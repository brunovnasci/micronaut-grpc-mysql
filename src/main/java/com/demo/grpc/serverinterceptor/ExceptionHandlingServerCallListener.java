package com.demo.grpc.serverinterceptor;

import com.demo.gateway.mysql.exceptions.ObjectNotFoundException;
import com.demo.grpc.exceptions.RequiredFieldException;
import io.grpc.ForwardingServerCallListener;
import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.Status;

class ExceptionHandlingServerCallListener<I, O> extends ForwardingServerCallListener.SimpleForwardingServerCallListener<I> {

    private final ServerCall<I, O> serverCall;
    private final Metadata metadata;

    ExceptionHandlingServerCallListener(ServerCall.Listener<I> listener, ServerCall<I, O> serverCall, Metadata metadata) {
        super(listener);
        this.serverCall = serverCall;
        this.metadata = metadata;
    }

    @Override
    public void onHalfClose() {
        try {
            super.onHalfClose();
        } catch (Exception ex) {
            handleException(ex, serverCall, metadata);
            throw ex;
        }
    }

    @Override
    public void onReady() {
        try {
            super.onReady();
        } catch (Exception ex) {
            handleException(ex, serverCall, metadata);
            throw ex;
        }
    }

    private void handleException(Exception exception, ServerCall<I, O> serverCall, Metadata metadata) {
        if (exception instanceof ObjectNotFoundException) {
            serverCall.close(Status.NOT_FOUND.withDescription("Person not found"), metadata);
        } else if (exception instanceof RequiredFieldException) {
            serverCall.close(Status.INVALID_ARGUMENT.withDescription(exception.getMessage()), metadata);
        } else {
            serverCall.close(Status.INTERNAL, metadata);
        }
    }
}