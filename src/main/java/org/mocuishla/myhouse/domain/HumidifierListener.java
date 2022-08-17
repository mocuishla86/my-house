package org.mocuishla.myhouse.domain;

import org.mocuishla.myhouse.domain.ports.Action;
import org.mocuishla.myhouse.domain.ports.ActionRepository;
import org.mocuishla.myhouse.domain.ports.ActionType;
import org.mocuishla.myhouse.domain.ports.AirConditioner;

import java.time.LocalDateTime;

public class HumidifierListener implements HouseStateListener {

    private AirConditioner airConditioner;
    private ActionRepository actionRepository;

    public HumidifierListener(AirConditioner airConditioner, ActionRepository actionRepository) {
        this.airConditioner = airConditioner;
        this.actionRepository = actionRepository;
    }

    @Override
    public void onStateChanged(HouseState newState) {
        int humidity = newState.getHumidity();
        if (humidity < 30 && !airConditioner.isHumidifierOn()) {
            airConditioner.switchOnHumidifier();
            this.actionRepository.saveAction(
                    new Action(LocalDateTime.now(), ActionType.TurnHumidifierOn, newState.getTemperature(), humidity));
        }
        if (humidity >= 30 && airConditioner.isHumidifierOn()) {
            airConditioner.switchOffHumidifier();
            this.actionRepository.saveAction(
                    new Action(LocalDateTime.now(), ActionType.TurnHumidifierOff, newState.getTemperature(), humidity));
        }
    }
}
