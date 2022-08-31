package org.mocuishla.myhouse.configuration;

import org.mocuishla.myhouse.domain.business.House;
import org.mocuishla.myhouse.domain.business.listeners.FreshAirListener;
import org.mocuishla.myhouse.domain.business.listeners.HouseStateListener;
import org.mocuishla.myhouse.domain.business.listeners.HumidifierListener;
import org.mocuishla.myhouse.domain.business.listeners.PrintHouseStateListener;
import org.mocuishla.myhouse.domain.business.model.HouseState;
import org.mocuishla.myhouse.domain.ports.ActionRepository;
import org.mocuishla.myhouse.domain.ports.AirConditioner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DomainConfiguration {

    @Bean
    public List<HouseStateListener> getListeners(AirConditioner airConditioner, ActionRepository actionRepository) {
        return List.of(
                new PrintHouseStateListener(),
                new HumidifierListener(airConditioner, actionRepository),
                new FreshAirListener(airConditioner, actionRepository)
        );
    }

    @Bean
    public House getHouse(HouseState houseState, ActionRepository actionRepository, List<HouseStateListener> listeners) {
        return new House(houseState, actionRepository, listeners);
    }

    @Bean
    public HouseState getHouseState() {
        return new HouseState();
    }

}
