package com.neon.banking.controller;

import com.neon.banking.dto.ManagerDto;
import com.neon.banking.model.Manager;
import com.neon.banking.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public ResponseEntity<ManagerDto> createManager(@RequestBody @Valid Manager manager, final BindingResult result) {

        managerService.checkIfUsernameIsUnique(manager.getUsername());

        if (result.hasErrors()) {
            managerService.handleModelConstraints(result.getFieldError().getDefaultMessage());
        }

        return new ResponseEntity(managerService.createManager(manager), HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ManagerDto>> getManagers() {

        return new ResponseEntity(managerService.getManagers(), HttpStatus.OK);
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ManagerDto> getManager(@PathVariable Long id) {

        ManagerDto managerDto = managerService.getManager(id);

        if (managerDto == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(managerDto,HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteManager(@PathVariable Long id) {

        ManagerDto managerDto = managerService.getManager(id);

        if (managerDto == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        managerService.deleteManager(managerDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity updateManager(@RequestBody @Valid Manager manager, @PathVariable Long id, final BindingResult result) {

        managerService.checkIfUsernameIsUnique(manager.getUsername());
        ManagerDto managerDtoRetrieved = managerService.getManager(id);

        if (managerDtoRetrieved == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        if (result.hasErrors()) {
            managerService.handleModelConstraints(result.getFieldError().getDefaultMessage());
        }

        manager.setId(managerDtoRetrieved.getId());

        managerService.updateManager(manager);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
