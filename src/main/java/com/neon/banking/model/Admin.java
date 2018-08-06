package com.neon.banking.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;

    @Column(name= "createdAt", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name= "updatedAt")
    @UpdateTimestamp
    private LocalDateTime updatedDate;
}
