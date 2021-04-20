package com.demo.gateway.mysql;

import com.demo.domain.Person;
import com.demo.gateway.CreatePersonGateway;
import com.demo.gateway.mysql.model.PersonDatabase;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
@RequiredArgsConstructor
public class CreatePersonGatewayImpl implements CreatePersonGateway {

    @Inject
    private final PersonRepository personRepository;

    @Override
    public Person execute(Person person) {
        PersonDatabase personDatabase = personRepository.save(PersonDatabase.builder()
                .nome(person.getNome())
                .idade(person.getIdade())
                .build());
        return Person.builder()
                .id(personDatabase.getId())
                .idade(personDatabase.getIdade())
                .nome(personDatabase.getNome()).build();
    }
}
