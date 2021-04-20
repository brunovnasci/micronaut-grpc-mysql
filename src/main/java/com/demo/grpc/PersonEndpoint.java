package com.demo.grpc;

import com.demo.*;
import com.demo.domain.Person;
import com.demo.usecase.CreatePersonUseCase;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;

import javax.inject.Singleton;

@Singleton
@RequiredArgsConstructor
public class PersonEndpoint extends PersonServiceGrpc.PersonServiceImplBase {

    private final CreatePersonUseCase createPersonUseCase;

    @Override
    public void create(PersonRequest request, StreamObserver<PersonReply> responseObserver) {
        Person person = createPersonUseCase.execute(Person.builder()
                .nome(request.getNome())
                .idade(request.getIdade())
                .build());

        responseObserver.onNext(PersonReply.newBuilder()
                .setNome(person.getNome())
                .setId(person.getId())
                .setIdade(person.getIdade())
                .build());
        responseObserver.onCompleted();
    }
}
