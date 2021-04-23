package com.demo.grpc.serverinterceptor;

import com.demo.grpc.serverinterceptor.handlers.ExceptionHandler;
import com.demo.grpc.serverinterceptor.handlers.GenericExceptionHandler;
import io.grpc.ForwardingServerCallListener;
import io.grpc.Metadata;
import io.grpc.ServerCall;

import java.util.List;

class ExceptionHandlingServerCallListener<I, O> extends ForwardingServerCallListener.SimpleForwardingServerCallListener<I> {

    private final ServerCall<I, O> serverCall;
    private final Metadata metadata;
    private final List<ExceptionHandler> exceptionHandler;

    ExceptionHandlingServerCallListener(ServerCall.Listener<I> listener, ServerCall<I, O> serverCall, Metadata metadata, List<ExceptionHandler> exceptionHandler) {
        super(listener);
        this.serverCall = serverCall;
        this.metadata = metadata;
        this.exceptionHandler = exceptionHandler;
    }

    @Override
    public void onHalfClose() {
        try {
            super.onHalfClose();
        } catch (Exception ex) {
            handleExceptionFactory(ex, serverCall, metadata);
            throw ex;
        }
    }

    @Override
    public void onReady() {
        try {
            super.onReady();
        } catch (Exception ex) {
            handleExceptionFactory(ex, serverCall, metadata);
            throw ex;
        }
    }

    private void handleExceptionFactory(Exception exception, ServerCall<I, O> serverCall, Metadata metadata) {
        for (ExceptionHandler handler : exceptionHandler) {
            if (handler.supports(exception)) {
                handler.handle(exception, serverCall, metadata);
                return;
            }
        }
        new GenericExceptionHandler().handle(exception, serverCall, metadata);
    }
}