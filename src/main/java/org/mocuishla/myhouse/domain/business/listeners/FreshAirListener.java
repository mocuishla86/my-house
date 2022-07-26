package org.mocuishla.myhouse.domain.business.listeners;

import org.mocuishla.myhouse.domain.business.model.Action;
import org.mocuishla.myhouse.domain.business.model.ActionType;
import org.mocuishla.myhouse.domain.business.model.HouseState;
import org.mocuishla.myhouse.domain.ports.ActionRepository;
import org.mocuishla.myhouse.domain.ports.AirConditioner;

import java.time.LocalDateTime;

public class FreshAirListener implements HouseStateListener {

    private AirConditioner airConditioner;
    private ActionRepository actionRepository;

    public FreshAirListener(AirConditioner airConditioner, ActionRepository actionRepository) {
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
