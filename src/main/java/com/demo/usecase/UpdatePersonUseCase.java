package com.demo.usecase;

import com.demo.domain.Person;
import com.demo.gateway.UpdatePersonGateway;
import lombok.RequiredArgsConstructor;

import javax.inject.Singleton;

@Singleton
@RequiredArgsConstructor
public class UpdatePersonUseCase {

    private final UpdatePersonGateway updatePersonGateway;

    public Person update(Person person){
      return updatePersonGateway.update(person);
    }
}
