package com.demo.gateway.mysql;

import com.demo.gateway.DeletePersonGateway;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
@RequiredArgsConstructor
public class DeletePersonGatewayImpl implements DeletePersonGateway {

    @Inject
    private final PersonRepository personRepository;

    @Override
    public void deleteById(Long id) {
        personRepository.deleteById(id);
    }
}
