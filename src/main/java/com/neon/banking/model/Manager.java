package com.neon.banking.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 4, message = "Username should have at least 4 characters")
    @Column(unique = true)
    private String username;

    @NotNull
    @Size(min = 5, message = "Password should have at least 5 characters")
    private String password;

    @NotNull(message = "First name cannot be null")
    @Size(min = 1, message = "First name should have at least 1 character")
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    @Size(min = 1, message = "Last name should have at least 1 character")
    private String lastName;

    @OneToMany(mappedBy = "manager", cascade = CascadeType.PERSIST)
    private Set<Customer> customers;

    @Column(name= "createdAt", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name= "updatedAt")
    @UpdateTimestamp
    private LocalDateTime updatedDate;

}
