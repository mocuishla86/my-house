package org.mocuishla.myhouse;

import org.mocuishla.myhouse.adapters.fake.FakeActionRepository;
import org.mocuishla.myhouse.adapters.fake.FakeAirConditioner;
import org.mocuishla.myhouse.domain.*;
import org.mocuishla.myhouse.domain.ports.ActionRepository;
import org.mocuishla.myhouse.domain.ports.AirConditioner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MyHouseConfiguration {

    @Bean
    public AirConditioner getAirConditioner() {
        return new FakeAirConditioner();
    }


    @Bean
    public ActionRepository getActionRepository() {
        return new FakeActionRepository();
    }

    @Bean
    public List<HouseStateListener> getListeners(AirConditioner airConditioner, ActionRepository actionRepository) {
        return List.of(
                new PrintHouseStateListener(),
                new HumidifierListener(airConditioner, actionRepository),
                new FreshAirListener(airConditioner, actionRepository)
        );
    }

    @Bean
    public House getHouse(HouseState houseState, ActionRepository actionRepository, List<HouseStateListener> listeners){
        return new House(houseState, actionRepository, listeners);
    }

    @Bean
    public HouseState getHouseState(){
        return new HouseState();
    }

}
