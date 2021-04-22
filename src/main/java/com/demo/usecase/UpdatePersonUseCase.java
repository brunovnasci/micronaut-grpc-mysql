package com.demo.usecase;

import com.demo.domain.Person;
import com.demo.gateway.UpdatePersonGateway;
import com.demo.gateway.mysql.mapper.PersonMapperImpl;
import lombok.RequiredArgsConstructor;

import javax.inject.Singleton;

@Singleton
@RequiredArgsConstructor
public class UpdatePersonUseCase {

    private final FindPersonUseCase findPersonUseCase;
    private final UpdatePersonGateway updatePersonGateway;

    public Person update(Person person) {
        Person personDatabase = findPersonUseCase.findById(person.getId());
        Person updatedPerson = new PersonMapperImpl().updateWithNullAsNoChange(person, personDatabase);
        return updatePersonGateway.update(updatedPerson);
    }
}
