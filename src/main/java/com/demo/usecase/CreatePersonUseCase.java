package com.demo.usecase;

import com.demo.domain.Person;
import com.demo.gateway.CreatePersonGateway;
import lombok.RequiredArgsConstructor;

import javax.inject.Singleton;

@RequiredArgsConstructor
@Singleton
public class CreatePersonUseCase {

    private final CreatePersonGateway createPersonGateway;

    public Person execute(Person person) {
        return createPersonGateway.execute(person);
    }
}
