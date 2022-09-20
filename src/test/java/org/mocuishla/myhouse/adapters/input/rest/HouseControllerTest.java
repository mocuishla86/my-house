package org.mocuishla.myhouse.adapters.input.rest;

import org.junit.jupiter.api.Test;
import org.mocuishla.myhouse.domain.business.House;
import org.mocuishla.myhouse.domain.ports.ActionRepository;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(properties = {"spring.liquibase.enabled=false"})
@EnableAutoConfiguration(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class})
class HouseControllerTest {

    @MockBean
    private House house;

    @MockBean
    private ActionRepository dummyActionRepository;


    @Test
    void registerTemperature() {
    }

    @Test
    void registerHumidity() {
    }

    @Test
    void getActions() {
    }
}