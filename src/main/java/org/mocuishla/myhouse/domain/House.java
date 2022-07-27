package org.mocuishla.myhouse.domain;

import org.mocuishla.myhouse.domain.ports.Action;
import org.mocuishla.myhouse.domain.ports.ActionRepository;
import org.mocuishla.myhouse.domain.ports.ActionType;
import org.mocuishla.myhouse.domain.ports.AirConditioner;

import java.time.LocalDateTime;
import java.util.List;

public class House {

    //private double temperature;
    //private int humidity;
    private HouseState houseState = new HouseState();
    private AirConditioner airConditioner;
    private ActionRepository actionRepository;

    public House(AirConditioner airConditioner, ActionRepository actionRepository) {
        this.airConditioner = airConditioner;
        this.actionRepository = actionRepository;
    }

    public void setTemperature(double temperature) {
        this.houseState.setTemperature(temperature);
        //this.temperature = temperature;
        if(temperature >= 30 && !airConditioner.isFreshAirOn()){
            airConditioner.switchOnFreshAir();
            this.actionRepository.saveAction(
                    new Action(LocalDateTime.now(), ActionType.TurnFreshAirOn, temperature, houseState.getHumidity())
            );
        }
        if (temperature <= 22 && airConditioner.isFreshAirOn()){
            airConditioner.switchOffFreshAir();
            this.actionRepository.saveAction(
                    new Action(LocalDateTime.now(), ActionType.TurnFreshAirOff, temperature, houseState.getHumidity())
            );
        }
    }

    public double getTemperature() {
        return houseState.getTemperature();
    }

    public void setHumidity(int humidity){
        this.houseState.setHumidity(humidity);
        if(humidity < 30 && !airConditioner.isHumidifierOn()){
            airConditioner.switchOnHumidifier();
            this.actionRepository.saveAction(
                    new Action(LocalDateTime.now(), ActionType.TurnHumidifierOn, houseState.getTemperature(), humidity));
        }
        if(humidity >= 30 && airConditioner.isHumidifierOn()){
            airConditioner.switchOffHumidifier();
            this.actionRepository.saveAction(
                    new Action(LocalDateTime.now(), ActionType.TurnHumidifierOff, houseState.getTemperature(), humidity));
        }
    }

    public int getHumidity(){
        return houseState.getHumidity();
    }

    public List<Action> getAllActions(){
        return actionRepository.getAllActions();
    }
}
