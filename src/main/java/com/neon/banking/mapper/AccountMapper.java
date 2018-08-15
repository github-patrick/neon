package com.neon.banking.mapper;

import com.neon.banking.dto.AccountDto;
import com.neon.banking.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountDto map(Account account);

    Account map(AccountDto accountDto);
}
