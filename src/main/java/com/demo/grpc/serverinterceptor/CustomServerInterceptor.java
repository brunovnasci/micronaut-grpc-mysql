package com.demo.grpc.serverinterceptor;

import io.grpc.*;

import javax.inject.Singleton;

@Singleton
public class CustomServerInterceptor implements ServerInterceptor {

    @Override
    public <I, O> ServerCall.Listener<I> interceptCall(ServerCall<I, O> serverCall, Metadata metadata, ServerCallHandler<I, O> serverCallHandler) {
//        metadata.get(Metadata.Key.of("aaa", Metadata.ASCII_STRING_MARSHALLER))
        ServerCall.Listener<I> listener = serverCallHandler.startCall(serverCall, metadata);
        return new ExceptionHandlingServerCallListener<>(listener, serverCall, metadata);
    }
}
