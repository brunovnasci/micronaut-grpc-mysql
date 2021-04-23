package com.demo.grpc;

import com.demo.*;
import com.demo.domain.Person;
import com.demo.grpc.exceptions.RequiredFieldException;
import com.demo.usecase.*;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;

import javax.inject.Singleton;
import java.util.List;

@Singleton
@RequiredArgsConstructor
public class PersonResource extends PersonServiceGrpc.PersonServiceImplBase {

    private final CreatePersonUseCase createPersonUseCase;
    private final FindPersonUseCase findPersonUseCase;
    private final UpdatePersonUseCase updatePersonUseCase;
    private final DeletePersonUseCase deletePersonUseCase;
    private final FindAllPersonUseCase findAllPersonUseCase;

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
    public void findById(FindPersonByIdRequest request, StreamObserver<PersonReply> responseObserver) {
        if (request.getId() == 0) {
            throw new RequiredFieldException("Person id must be provided");
        }

        Person person = findPersonUseCase.findById(request.getId());

        responseObserver.onNext(PersonReply.newBuilder()
                .setId(person.getId())
                .setNome(person.getNome())
                .setIdade(person.getIdade())
                .build());

        responseObserver.onCompleted();
    }

    @Override
    public void findAll(Empty request, StreamObserver<FindAllPersonReply> responseObserver) {
        List<Person> personList = findAllPersonUseCase.findAll();

        FindAllPersonReply.Builder builder = FindAllPersonReply.newBuilder();

        personList.forEach(person ->
                builder.addPersonList(PersonReply.newBuilder()
                        .setId(person.getId())
                        .setIdade(person.getIdade())
                        .setNome(person.getNome()))
        );

        responseObserver.onNext(builder.build());

        responseObserver.onCompleted();
    }

    @Override
    public void deleteById(DeletePersonByIdRequest request, StreamObserver<DeletePersonReply> responseObserver) {
        if (request.getId() == 0) {
            throw new RequiredFieldException("Person id must be provided");
        }

        deletePersonUseCase.deleteById(request.getId());

        responseObserver.onNext(DeletePersonReply.newBuilder()
                .setId(request.getId())
                .setMessage("User " + request.getId() + " Deleted")
                .build());

        responseObserver.onCompleted();
    }

    @Override
    public void updateById(UpdatePersonByIdRequest request, StreamObserver<PersonReply> responseObserver) {
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
