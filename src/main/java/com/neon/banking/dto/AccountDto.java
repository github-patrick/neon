package com.neon.banking.dto;

import com.neon.banking.model.Transaction;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    private Long id;
    private Double balance;
    private boolean blocked;

//    private Customer customer;

    private List<Transaction> transactions;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;
}
