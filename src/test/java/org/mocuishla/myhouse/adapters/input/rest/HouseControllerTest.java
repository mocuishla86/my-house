package org.mocuishla.myhouse.adapters.input.rest;

import org.junit.jupiter.api.Test;
import org.mocuishla.myhouse.domain.business.House;
import org.mocuishla.myhouse.domain.business.model.Action;
import org.mocuishla.myhouse.domain.business.model.ActionType;
import org.mocuishla.myhouse.domain.ports.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(properties = {"spring.liquibase.enabled=false"})
@EnableAutoConfiguration(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class})
@AutoConfigureMockMvc
class HouseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private House house;

    @MockBean
    private ActionRepository dummyActionRepository;


    @Test
    void registerTemperature() throws Exception {
        ResultActions result = mockMvc.perform(post("/temperature")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"temperature\": 38 }")
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
        verify(house).setTemperature(38);
    }

    @Test
    void registerHumidity() throws Exception {
        ResultActions result = mockMvc.perform(post("/humidity")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"humidity\": 40 }")
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
        verify(house).setHumidity(40);
    }

    @Test
    void getActions() throws Exception {
        when(house.getAllActions()).thenReturn(List.of(
                new Action(
                        UUID.fromString("6d455266-705b-4efc-ba64-a98a7d637bdb"),
                        LocalDateTime.of(2007, 3, 2, 0, 22, 2, 45),
                        ActionType.TurnFreshAirOn,
                        37,
                        21
                ),
                new Action(
                        UUID.fromString("19416dbb-83cb-43cb-b34b-02219f584950"),
                        LocalDateTime.of(2017, 3, 2, 0, 22, 2, 45),
                        ActionType.TurnFreshAirOff,
                        -38,
                        22
                )
        ));

        ResultActions result = mockMvc.perform(get("/actions")
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value("6d455266-705b-4efc-ba64-a98a7d637bdb"))
                .andExpect(jsonPath("$[0].timestamp").value("2007-03-02T00:22:02.000000045"))
                .andExpect(jsonPath("$[0].type").value("TurnFreshAirOn"))
                .andExpect(jsonPath("$[0].temperature").value(37))
                .andExpect(jsonPath("$[0].humidity").value(21))
                .andExpect(jsonPath("$[1].id").value("19416dbb-83cb-43cb-b34b-02219f584950"))
                .andExpect(jsonPath("$[1].timestamp").value("2017-03-02T00:22:02.000000045"))
                .andExpect(jsonPath("$[1].type").value("TurnFreshAirOff"))
                .andExpect(jsonPath("$[1].temperature").value(-38))
                .andExpect(jsonPath("$[1].humidity").value(22));
    }
}