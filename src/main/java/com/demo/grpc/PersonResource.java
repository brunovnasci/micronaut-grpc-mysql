package com.demo.grpc;

import com.demo.*;
import com.demo.domain.Person;
import com.demo.grpc.exceptions.RequiredFieldException;
import com.demo.usecase.CreatePersonUseCase;
import com.demo.usecase.DeletePersonUseCase;
import com.demo.usecase.FindPersonUseCase;
import com.demo.usecase.UpdatePersonUseCase;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;

import javax.inject.Singleton;

@Singleton
@RequiredArgsConstructor
public class PersonResource extends PersonServiceGrpc.PersonServiceImplBase {

    private final CreatePersonUseCase createPersonUseCase;
    private final FindPersonUseCase findPersonUseCase;
    private final UpdatePersonUseCase updatePersonUseCase;
    private final DeletePersonUseCase deletePersonUseCase;

    @Override
    public void create(CreatePersonRequest request, StreamObserver<PersonReply> responseObserver) {
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
    public void findById(FindPersonRequest request, StreamObserver<PersonReply> responseObserver) {
        Person person = findPersonUseCase.findById(request.getId());

        responseObserver.onNext(PersonReply.newBuilder()
                .setId(person.getId())
                .setNome(person.getNome())
                .setIdade(person.getIdade())
                .build());

        responseObserver.onCompleted();
    }

    @Override
    public void deleteById(FindPersonRequest request, StreamObserver<DeletePersonRequest> responseObserver) {
        if (request.getId() == 0) {
            throw new RequiredFieldException("Person id must be provided");
        }

        deletePersonUseCase.deleteById(request.getId());

        responseObserver.onNext(DeletePersonRequest.newBuilder()
                .setId(request.getId())
                .setMessage("User " + request.getId() + " Deleted")
                .build());

        responseObserver.onCompleted();
    }

    @Override
    public void update(UpdatePersonRequest request, StreamObserver<PersonReply> responseObserver) {
        if (request.getId() == 0) {
            throw new RequiredFieldException("Person id must be provided");
        }

        Person person = updatePersonUseCase.update(Person.builder()
                .id(request.getId())
                .nome(request.getNome().isEmpty() ? null : request.getNome())
                .idade(request.getIdade().isEmpty() ? null : request.getIdade())
                .build());

        responseObserver.onNext(PersonReply.newBuilder()
                .setId(person.getId())
                .setNome(person.getNome())
                .setIdade(person.getIdade())
                .build());
        responseObserver.onCompleted();
    }
}
