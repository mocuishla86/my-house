package org.mocuishla.myhouse.domain;

import org.mocuishla.myhouse.domain.ports.Action;
import org.mocuishla.myhouse.domain.ports.ActionRepository;
import org.mocuishla.myhouse.domain.ports.ActionType;
import org.mocuishla.myhouse.domain.ports.AirConditioner;

import java.time.LocalDateTime;
import java.util.List;

public class House {

    private HouseState houseState = new HouseState();
    private AirConditioner airConditioner;
    private ActionRepository actionRepository;
    private List<HouseStateListener> houseStateListeners;

    public House(AirConditioner airConditioner, ActionRepository actionRepository) {
        this.airConditioner = airConditioner;
        this.actionRepository = actionRepository;
        this.houseStateListeners = List.of(
                new HumidifierListener(airConditioner, actionRepository),
                new FreshAirListener(airConditioner, actionRepository),
                new PrintHouseStateListener()
        );
    }

    public void setTemperature(double temperature) {
        this.houseState.setTemperature(temperature);
        this.houseStateListeners.forEach(listener -> listener.onStateChanged(houseState));
    }

    public double getTemperature() {
        return houseState.getTemperature();
    }

    public void setHumidity(int humidity){
        this.houseState.setHumidity(humidity);
        this.houseStateListeners.forEach(listener -> listener.onStateChanged(houseState));
    }

    public int getHumidity(){
        return houseState.getHumidity();
    }

    public List<Action> getAllActions(){
        return actionRepository.getAllActions();
    }
}
