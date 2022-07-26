package org.mocuishla.myhouse.domain;

import org.mocuishla.myhouse.domain.ports.Action;
import org.mocuishla.myhouse.domain.ports.ActionRepository;
import org.mocuishla.myhouse.domain.ports.ActionType;
import org.mocuishla.myhouse.domain.ports.AirConditioner;

import java.time.LocalDateTime;
import java.util.List;

public class House {

    private double temperature;
    private int humidity;
    private AirConditioner airConditioner;
    private ActionRepository actionRepository;

    public House(AirConditioner airConditioner, ActionRepository actionRepository) {
        this.airConditioner = airConditioner;
        this.actionRepository = actionRepository;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
        if(temperature >= 30 && !airConditioner.isFreshAirOn()){
            airConditioner.switchOnFreshAir();
            this.actionRepository.saveAction(
                    new Action(LocalDateTime.now(), ActionType.TurnFreshAirOn, temperature, humidity)
            );
        }
        if (temperature <= 22 && airConditioner.isFreshAirOn()){
            airConditioner.switchOffFreshAir();
            this.actionRepository.saveAction(
                    new Action(LocalDateTime.now(), ActionType.TurnFreshAirOff, temperature, humidity)
            );
        }
    }

    public double getTemperature() {
        return temperature;
    }

    public void setHumidity(int humidity){
        this.humidity = humidity;
        if(humidity < 30 && !airConditioner.isHumidifierOn()){
            airConditioner.switchOnHumidifier();
            this.actionRepository.saveAction(
                    new Action(LocalDateTime.now(), ActionType.TurnHumidifierOn, temperature, humidity));
        }
        if(humidity >= 30 && airConditioner.isHumidifierOn()){
            airConditioner.switchOffHumidifier();
            this.actionRepository.saveAction(
                    new Action(LocalDateTime.now(), ActionType.TurnHumidifierOff, temperature, humidity));
        }
    }

    public int getHumidity(){
        return humidity;
    }

    public List<Action> getAllActions(){
        return actionRepository.getAllActions();
    }
}
