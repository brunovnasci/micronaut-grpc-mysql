package com.demo.gateway.mysql;

import com.demo.gateway.mysql.model.PersonDatabase;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface PersonRepository extends CrudRepository<PersonDatabase, Long> {
}
