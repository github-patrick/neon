package com.neon.banking.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.neon.banking.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ManagerDto {

    private Long id;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private List<CustomerDto> customers;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;
}
