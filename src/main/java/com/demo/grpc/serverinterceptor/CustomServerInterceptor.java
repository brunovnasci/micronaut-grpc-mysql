package com.demo.grpc.serverinterceptor;

import com.demo.grpc.serverinterceptor.handlers.ExceptionHandler;
import io.grpc.*;
import lombok.RequiredArgsConstructor;

import javax.inject.Singleton;
import java.util.List;

@Singleton
@RequiredArgsConstructor
public class CustomServerInterceptor implements ServerInterceptor {

    private final List<ExceptionHandler> exceptionHandler;

    @Override
    public <I, O> ServerCall.Listener<I> interceptCall(ServerCall<I, O> serverCall, Metadata metadata, ServerCallHandler<I, O> serverCallHandler) {
//        metadata.get(Metadata.Key.of("aaa", Metadata.ASCII_STRING_MARSHALLER))
        ServerCall.Listener<I> listener = serverCallHandler.startCall(serverCall, metadata);
        return new ExceptionHandlingServerCallListener<>(listener, serverCall, metadata, exceptionHandler);
    }
}
