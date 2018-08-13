package com.neon.banking.repository;

import com.neon.banking.model.Manager;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends CrudRepository<Manager, Long> {

    Manager findByUsernameIgnoreCase(String username);

}
