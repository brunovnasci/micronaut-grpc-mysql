package com.demo.grpc;

import com.demo.CreatePersonRequest;
import com.demo.FindPersonRequest;
import com.demo.PersonReply;
import com.demo.PersonServiceGrpc;
import com.demo.domain.Person;
import com.demo.usecase.CreatePersonUseCase;
import com.demo.usecase.FindPersonUseCase;
import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;

import javax.inject.Singleton;
import java.util.Optional;

@Singleton
@RequiredArgsConstructor
public class PersonResource extends PersonServiceGrpc.PersonServiceImplBase {

    private final CreatePersonUseCase createPersonUseCase;

    private final FindPersonUseCase findPersonUseCase;

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
        Optional<Person> person = findPersonUseCase.findById(request.getId());

        if (person.isPresent()) {
            responseObserver.onNext(PersonReply.newBuilder()
                    .setId(person.get().getId())
                    .setNome(person.get().getNome())
                    .setIdade(person.get().getIdade())
                    .build());
        } else {
            responseObserver.onError(new StatusException(Status.NOT_FOUND));
        }

        responseObserver.onCompleted();
    }


}
