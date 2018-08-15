package com.neon.banking.mapper;

import com.neon.banking.dto.CustomerDto;
import com.neon.banking.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDto map(Customer customer);

    Customer map(CustomerDto customerDto);

}
