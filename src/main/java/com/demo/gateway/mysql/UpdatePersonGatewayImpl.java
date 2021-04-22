package com.demo.gateway.mysql;

import com.demo.domain.Person;
import com.demo.gateway.UpdatePersonGateway;
import com.demo.gateway.mysql.model.PersonDatabase;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
@RequiredArgsConstructor
public class UpdatePersonGatewayImpl implements UpdatePersonGateway {

    @Inject
    private final PersonRepository personRepository;

    @Override
    public Person update(Person person) {
        PersonDatabase personDatabase = personRepository.update(PersonDatabase.builder()
                .id(person.getId())
                .nome(person.getNome())
                .idade(person.getIdade())
                .build());

        return Person.builder()
                .id(personDatabase.getId())
                .idade(personDatabase.getIdade())
                .nome(personDatabase.getNome())
                .build();
    }
}
