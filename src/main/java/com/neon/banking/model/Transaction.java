package com.neon.banking.model;

import com.neon.banking.model.types.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TransactionType type;
    
    private Double amount;

    @ManyToOne
    private Account payer;

    @ManyToOne
    private Account payee;

    @Column(name= "createdAt", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name= "updatedAt")
    @UpdateTimestamp
    private LocalDateTime updatedDate;
}
