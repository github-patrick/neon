package com.neon.banking.mapper;

import com.neon.banking.dto.CustomerDto;
import com.neon.banking.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDto map(Customer customer);

    Customer map(CustomerDto customerDto);

}
