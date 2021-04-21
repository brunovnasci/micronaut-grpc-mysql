package com.demo.gateway.mysql.mapper;

import com.demo.domain.Person;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper
public interface PersonMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Person updateWithNullAsNoChange(Person person, @MappingTarget Person personDatabase);
}
