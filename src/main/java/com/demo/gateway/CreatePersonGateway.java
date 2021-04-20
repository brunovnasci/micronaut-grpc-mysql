package com.demo.gateway;

import com.demo.domain.Person;

public interface CreatePersonGateway {

    Person execute(Person person);

}
