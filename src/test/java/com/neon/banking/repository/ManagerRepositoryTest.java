package com.neon.banking.repository;

import com.neon.banking.model.Manager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class ManagerRepositoryTest {

    @Autowired
    private ManagerRepository managerRepository;

    private static Validator validator;

    private Manager manager;

    @Before
    public void setUp() {
        manager = Manager.builder().firstName("Mark").lastName("Matthew").username("username")
                .password("password").customers(null).build();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    @Test
    public void shouldPersistDataToDb() {

        managerRepository.save(manager);

        Manager retrievedManager = managerRepository.findById(1L).get();

        assertThat(retrievedManager.getId(), is(equalTo(1L)));
        assertThat(retrievedManager.getFirstName(), is(equalTo("Mark")));
        assertThat(retrievedManager.getLastName(), is(equalTo("Matthew")));
    }

    // Constraints

    @Test
    public void shouldHaveAValidPassword() {
        manager.setPassword("pass");
        Set<ConstraintViolation<Manager>> violations = validator.validate(manager);

        assertFalse(violations.isEmpty());
        assertEquals("Password should have at least 5 characters", violations.iterator().next().getConstraintDescriptor().getMessageTemplate());
    }

    @Test
    public void shouldHaveAValidUsername() {
        manager.setUsername("jun");
        Set<ConstraintViolation<Manager>> violations = validator.validate(manager);

        assertFalse(violations.isEmpty());
        assertEquals("Username should have at least 4 characters", violations.iterator().next().getConstraintDescriptor().getMessageTemplate());
    }

    @Test
    public void shouldHaveAFirstName() {
        manager.setFirstName(null);
        Set<ConstraintViolation<Manager>> violations = validator.validate(manager);

        assertFalse(violations.isEmpty());
        assertEquals("First name cannot be null", violations.iterator().next().getConstraintDescriptor().getMessageTemplate());

        manager.setFirstName("");
        violations = validator.validate(manager);

        assertFalse(violations.isEmpty());
        assertEquals("First name should have at least 1 character", violations.iterator().next().getConstraintDescriptor().getMessageTemplate());
    }

    @Test
    public void shouldHaveALastName() {
        manager.setLastName(null);
        Set<ConstraintViolation<Manager>> violations = validator.validate(manager);

        assertFalse(violations.isEmpty());
        assertEquals("Last name cannot be null", violations.iterator().next().getConstraintDescriptor().getMessageTemplate());

        manager.setLastName("");
        violations = validator.validate(manager);

        assertFalse(violations.isEmpty());
        assertEquals("Last name should have at least 1 character", violations.iterator().next().getConstraintDescriptor().getMessageTemplate());
    }








}