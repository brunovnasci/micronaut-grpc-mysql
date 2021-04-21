package com.demo.gateway;

import com.demo.domain.Person;

import java.util.Optional;

public interface FindPersonGateway {

    Optional<Person> findById(Long id);
}
