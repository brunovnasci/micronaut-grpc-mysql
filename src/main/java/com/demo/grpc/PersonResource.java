package com.demo.grpc;

import com.demo.*;
import com.demo.domain.Person;
import com.demo.usecase.CreatePersonUseCase;
import com.demo.usecase.FindPersonUserCase;
import io.grpc.stub.StreamObserver;
import io.micronaut.http.annotation.Controller;
import lombok.RequiredArgsConstructor;

import javax.inject.Singleton;

@Singleton
@RequiredArgsConstructor
public class PersonResource extends PersonServiceGrpc.PersonServiceImplBase {

    private final CreatePersonUseCase createPersonUseCase;

    private final FindPersonUserCase findPersonUserCase;

    @Override
    public void create(PersonRequest request, StreamObserver<PersonReply> responseObserver) {
        Person person = createPersonUseCase.execute(Person.builder()
                .nome(request.getNome())
                .idade(request.getIdade())
                .build());

        responseObserver.onNext(PersonReply.newBuilder()
                .setId(person.getId())
                .setNome(person.getNome())
                .setIdade(person.getIdade())
                .build());
        responseObserver.onCompleted();
    }

    @Override
    public void findById(PersonRequest request, StreamObserver<PersonReply> responseObserver) {
        Person person = findPersonUserCase.findById(request.getId());

        responseObserver.onNext(PersonReply.newBuilder()
                .setId(person.getId())
                .setNome(person.getNome())
                .setIdade(person.getIdade())
                .build());
        responseObserver.onCompleted();
    }




}
