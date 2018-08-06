package com.neon.banking.repository;

import com.neon.banking.model.Manager;
import org.springframework.data.repository.CrudRepository;

public interface ManagerRepository extends CrudRepository<Manager, Long> {

}
