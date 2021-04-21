package com.demo.gateway;

import com.demo.domain.Person;

public interface FindPersonGateway {

    Person findById(Long id);
}
