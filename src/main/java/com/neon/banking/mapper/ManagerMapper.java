package com.neon.banking.mapper;

import com.neon.banking.dto.ManagerDto;
import com.neon.banking.model.Manager;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ManagerMapper {

    ManagerMapper INSTANCE = Mappers.getMapper(ManagerMapper.class);


    ManagerDto map(Manager manager);
    Manager map(ManagerDto managerDto);
}
