package com.neon.banking.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
@ToString
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @NotNull(message = "Username cannot be null")
    @Size(min = 4, message = "Username should have at least 4 characters")
    @Column(unique = true)
    protected String username;

    @NotNull(message = "Password cannot be null")
    @Size(min = 5, message = "Password should have at least 5 characters")
    protected String password;

    @NotNull(message = "First name cannot be null")
    @Size(min = 1, message = "First name should have at least 1 character")
    protected String firstName;

    @NotNull(message = "Last name cannot be null")
    @Size(min = 1, message = "Last name should have at least 1 character")
    protected String lastName;

    @Column(name= "createdAt", updatable = false)
    @CreationTimestamp
    protected LocalDateTime createdDate;

    @Column(name= "updatedAt")
    @UpdateTimestamp
    protected LocalDateTime updatedDate;
}
