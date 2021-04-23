package com.demo.usecase;

import com.demo.domain.Person;
import com.demo.gateway.FindAllPersonGateway;
import lombok.RequiredArgsConstructor;

import javax.inject.Singleton;
import java.util.List;

@Singleton
@RequiredArgsConstructor
public class FindAllPersonUseCase {

    private final FindAllPersonGateway findAllPersonGateway;

    public List<Person> findAll(){
        return findAllPersonGateway.findAll();
    }
}
