package com.demo.gateway.mysql;

import com.demo.domain.Person;
import com.demo.gateway.FindPersonGateway;
import com.demo.gateway.mysql.exceptions.ObjectNotFoundException;
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
    public Person findById(Long id){
        Optional<PersonDatabase> obj = personRepository.findById(id);

        return Person.builder()
                .id(obj.get().getId())
                .idade(obj.get().getIdade())
                .nome(obj.get().getNome()).build();
    }

}
