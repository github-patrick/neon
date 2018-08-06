package com.neon.banking.repository;

import com.neon.banking.model.Manager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class ManagerRepositoryTest {

    @Autowired
    private ManagerRepository managerRepository;

    private Manager manager;

    @Before
    public void setUp() {
        manager = Manager.builder().firstName("Mark").lastName("Matthew").username("markup")
                .password("password").customers(null).build();
    }


    @Test
    public void shouldPersistDataToDb() {
        managerRepository.save(manager);

        Manager retrievedManager = managerRepository.findById(1L).get();

        assertThat(retrievedManager.getId(), is(equalTo(1L)));
        assertThat(retrievedManager.getFirstName(), is(equalTo("Mark")));
        assertThat(retrievedManager.getLastName(), is(equalTo("Matthew")));

    }


}