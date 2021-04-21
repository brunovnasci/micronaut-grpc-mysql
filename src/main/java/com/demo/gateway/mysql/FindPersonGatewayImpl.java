package com.demo.gateway.mysql;

import com.demo.domain.Person;
import com.demo.gateway.FindPersonGateway;
import com.demo.gateway.mysql.model.PersonDatabase;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
@RequiredArgsConstructor
public class FindPersonGatewayImpl implements FindPersonGateway {

    @Inject
    private final PersonRepository personRepository;

    @Override
    public Optional<Person> findById(Long id) {
        Optional<PersonDatabase> personDatabase = personRepository.findById(id);

        return personDatabase.map(database -> Person.builder()
                .id(database.getId())
                .idade(database.getIdade())
                .nome(database.getNome()).build());

    }

}
