package com.demo.usecase;

import com.demo.domain.Person;
import com.demo.gateway.DeletePersonGateway;
import lombok.RequiredArgsConstructor;

import javax.inject.Singleton;

@Singleton
@RequiredArgsConstructor
public class DeletePersonUseCase {

    private final DeletePersonGateway deletePersonGateway;
    private final FindPersonUseCase findPersonUseCase;

    public void deleteById(Long id){
        Person person = findPersonUseCase.findById(id);
        deletePersonGateway.deleteById(person.getId());
    }
}
