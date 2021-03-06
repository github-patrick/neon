package com.neon.banking.service;

import com.neon.banking.dto.ManagerDto;
import com.neon.banking.exceptions.ManagerNotFoundException;
import com.neon.banking.exceptions.ModelConstraintErrorException;
import com.neon.banking.exceptions.UsernameExistsException;
import com.neon.banking.mapper.ManagerMapper;
import com.neon.banking.model.Manager;
import com.neon.banking.repository.ManagerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ManagerService {

    private ManagerRepository managerRepository;

    private ManagerMapper managerMapper;


    public ManagerService(ManagerRepository managerRepository, ManagerMapper managerMapper) {
        this.managerRepository = managerRepository;
        this.managerMapper = managerMapper;
    }

    public ManagerDto createManager(Manager manager) {

        Manager managerSaved = managerRepository.save(manager);
        ManagerDto managerDtoSaved = managerMapper.map(managerSaved);

        return managerDtoSaved;
    }

    public List<ManagerDto> getManagers() {

        List<ManagerDto> managers = new ArrayList<>();
        Iterator<Manager> iterator = managerRepository.findAll().iterator();

        while (iterator.hasNext()) {
            managers.add(managerMapper.map(iterator.next()));
        }
        return managers;
    }

    public ManagerDto getManager(Long id) {
        Optional<Manager> manager = managerRepository.findById(id);
        if (!manager.isPresent()) {
            return null;
        }

        ManagerDto managerDto = managerMapper.map(manager.get());
        return managerDto;
    }

    public void deleteManager(ManagerDto managerDto) {
        Manager manager = managerMapper.map(managerDto);
        managerRepository.delete(manager);
    }

    public void updateManager(Manager manager) {
        managerRepository.save(manager);
    }

    public void checkIfUsernameIsUnique(String username) {

        if (managerRepository.findByUsernameIgnoreCase(username) != null) {
            throw new UsernameExistsException("Username exists");
        }
    }

    public void handleModelConstraints(String errorMessage) {
        throw new ModelConstraintErrorException(errorMessage);
    }

    public void checkIfManagerExists(Long id) {
        if (!managerRepository.findById(id).isPresent()) {
            throw new ManagerNotFoundException("manager of id " +id+ "does not exist");
        }
    }
}
