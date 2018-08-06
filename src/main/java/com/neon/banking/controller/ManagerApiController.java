package com.neon.banking.controller;

import com.neon.banking.model.Customer;
import com.neon.banking.model.Manager;
import com.neon.banking.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Optional;

@RestController
@RequestMapping(ManagerApiController.RESOURCE_PATH)
public class ManagerApiController {

    private ManagerService managerService;
    public static final String RESOURCE_PATH = "/managers";


    @Autowired
    public ManagerApiController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createManager(@RequestBody Manager manager) {

        managerService.createManager(manager);

        return new ResponseEntity(manager, HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getManagers() {

        return new ResponseEntity(managerService.getManagers(), HttpStatus.OK);
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Manager> getManager(@PathVariable Long id) {

        Manager manager = managerService.getManager(id);

        if (manager == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(manager,HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteManager(@PathVariable Long id) {

        Manager manager = managerService.getManager(id);

        if (manager == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        managerService.deleteManager(manager);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity updateManager(@RequestBody Manager manager, @PathVariable Long id) {

        if (managerService.getManager(id) == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        manager.setId(id);
        managerService.updateManager(manager);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }





}
