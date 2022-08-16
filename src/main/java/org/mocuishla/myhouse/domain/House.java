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
    private HouseStateListener houseStateListener;
    private FreshAirListener freshAirListener;
    private HumidifierListener humidifierListener;

    public House(AirConditioner airConditioner, ActionRepository actionRepository) {
        this.airConditioner = airConditioner;
        this.actionRepository = actionRepository;
        humidifierListener = new HumidifierListener(airConditioner, actionRepository);
        freshAirListener = new FreshAirListener(airConditioner, actionRepository);
        houseStateListener = new PrintHouseStateListener();
    }

    public void setTemperature(double temperature) {
        this.houseState.setTemperature(temperature);
        this.houseStateListener.onStateChanged(houseState);
        this.freshAirListener.onStateChanged(houseState);
        this.humidifierListener.onStateChanged(houseState);
    }

    public double getTemperature() {
        return houseState.getTemperature();
    }

    public void setHumidity(int humidity){
        this.houseState.setHumidity(humidity);
        this.houseStateListener.onStateChanged(houseState);
        this.freshAirListener.onStateChanged(houseState);
        this.humidifierListener.onStateChanged(houseState);

    }

    public int getHumidity(){
        return houseState.getHumidity();
    }

    public List<Action> getAllActions(){
        return actionRepository.getAllActions();
    }
}
