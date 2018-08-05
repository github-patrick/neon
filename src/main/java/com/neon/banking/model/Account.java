package com.neon.banking.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double balance;
    private boolean blocked;

    @ManyToOne
    private Customer customer;
}
