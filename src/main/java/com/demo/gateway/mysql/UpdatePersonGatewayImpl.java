package com.demo.gateway.mysql;

import com.demo.domain.Person;
import com.demo.gateway.FindPersonGateway;
import com.demo.gateway.UpdatePersonGateway;
import com.demo.gateway.mysql.model.PersonDatabase;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
@RequiredArgsConstructor
public class UpdatePersonGatewayImpl implements UpdatePersonGateway {

    @Inject
    private PersonRepository personRepository;

    @Override
    public Person update(Person person) {
        Optional<PersonDatabase> newOBJ = personRepository.findById(person.getId());
        updatePerson(newOBJ,person);

        PersonDatabase personDatabase = personRepository.save(PersonDatabase.builder()
                .nome(newOBJ.get().getNome())
                .idade(newOBJ.get().getIdade())
                .build());

        return Person.builder()
                .id(personDatabase.getId())
                .idade(personDatabase.getIdade())
                .nome(personDatabase.getNome()).build();
    }

    private void updatePerson(Optional<PersonDatabase> newOBJ, Person person) {
        newOBJ.get().setNome(person.getNome());
        newOBJ.get().setIdade(person.getIdade());
    }
}
