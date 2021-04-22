package com.demo.gateway.mysql;

import com.demo.domain.Person;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Singleton
public class FindAllPersonGatewayImpl {

    @Inject
    private final PersonRepository personRepository;

    public List<Person> findAll() {

        return StreamSupport.stream(personRepository.findAll().spliterator(), true)
                .map(personDatabase -> Person.builder()
                        .id(personDatabase.getId())
                        .nome(personDatabase.getNome())
                        .idade(personDatabase.getIdade())
                        .build())
                .collect(Collectors.toList());
    }
}
