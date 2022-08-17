package org.mocuishla.myhouse.domain;

import org.mocuishla.myhouse.domain.ports.Action;
import org.mocuishla.myhouse.domain.ports.ActionRepository;
import org.mocuishla.myhouse.domain.ports.ActionType;
import org.mocuishla.myhouse.domain.ports.AirConditioner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class FreshAirListener implements HouseStateListener {

    private AirConditioner airConditioner;
    private ActionRepository actionRepository;

    public FreshAirListener(AirConditioner airConditioner, ActionRepository actionRepository){
        this.airConditioner = airConditioner;
        this.actionRepository = actionRepository;
    }

    @Override
    public void onStateChanged(HouseState newState) {
        double temperature = newState.getTemperature();
        if (temperature >= 30 && !airConditioner.isFreshAirOn()) {
            airConditioner.switchOnFreshAir();
            this.actionRepository.saveAction(
                    new Action(LocalDateTime.now(), ActionType.TurnFreshAirOn, temperature, newState.getHumidity())
            );
        }
        if (temperature <= 22 && airConditioner.isFreshAirOn()) {
            airConditioner.switchOffFreshAir();
            this.actionRepository.saveAction(
                    new Action(LocalDateTime.now(), ActionType.TurnFreshAirOff, temperature, newState.getHumidity())
            );

        }
    }
}
