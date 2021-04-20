package com.demo.usecase;

import com.demo.domain.Person;
import com.demo.gateway.FindPersonGateway;
import lombok.RequiredArgsConstructor;

import javax.inject.Singleton;
import java.util.Optional;

@Singleton
@RequiredArgsConstructor
public class FindPersonUseCase {

    private final FindPersonGateway findPersonGateway;

    public Optional<Person> findById(long id) {
        return findPersonGateway.findById(id);
    }
}
