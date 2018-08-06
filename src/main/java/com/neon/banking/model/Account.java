package com.neon.banking.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double balance;
    private boolean blocked;

    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "payer", cascade = CascadeType.PERSIST)
    private Set<Transaction> transactions;

    @Column(name= "createdAt", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name= "updatedAt")
    @UpdateTimestamp
    private LocalDateTime updatedDate;
}
