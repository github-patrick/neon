package com.neon.banking.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
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
    private String username;
    private String password;
    private String firstName;
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
