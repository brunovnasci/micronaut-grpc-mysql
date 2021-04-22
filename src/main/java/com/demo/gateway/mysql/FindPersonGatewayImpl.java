package com.demo.gateway.mysql;

import com.demo.domain.Person;
import com.demo.gateway.FindPersonGateway;
import com.demo.gateway.mysql.exceptions.ObjectNotFoundException;
import com.demo.gateway.mysql.model.PersonDatabase;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
@RequiredArgsConstructor
public class FindPersonGatewayImpl implements FindPersonGateway {

    @Inject
    private final PersonRepository personRepository;

    @Override
    public Person findById(Long id) {
        PersonDatabase personDatabase = personRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("[GATEWAY] Person with id - " + id + " not found"));

        return Person.builder()
                .id(personDatabase.getId())
                .idade(personDatabase.getIdade())
                .nome(personDatabase.getNome()).build();
    }



}
