package com.neon.banking.service;

import com.neon.banking.model.Manager;
import com.neon.banking.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ManagerService {

    @Autowired
    private ManagerRepository managerRepository;

    public void createManager(Manager manager) {
        managerRepository.save(manager);
    }

    public List<Manager> getManagers() {

        List<Manager> managers = new ArrayList<>();
        managerRepository.findAll().forEach(managers::add);
        return managers;
    }

    public Manager getManager(Long id) {
        Optional<Manager> manager = managerRepository.findById(id);
        if (!manager.isPresent()) {
            return null;
        }
        return manager.get();
    }

    public void deleteManager(Manager manager) {
        managerRepository.delete(manager);
    }

    public void updateManager(Manager manager) {
        managerRepository.save(manager);
    }
}
