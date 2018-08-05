package com.neon.banking.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;

    @ManyToOne
    private Manager manager;

    @OneToMany(mappedBy = "customer")
    private Set<Account> accounts;

}
