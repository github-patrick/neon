package com.neon.banking.dto;

import com.neon.banking.model.Account;
import com.neon.banking.model.Manager;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {


    private Long id;

    private String firstName;

    private String lastName;

    private String username;

    private String password;

//    @JsonIgnore
//    private ManagerDto managerDto;

    private List<AccountDto> accounts;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;
}
