package org.mocuishla.myhouse.domain;

import org.mocuishla.myhouse.domain.ports.AirConditioner;

public class House {

    private double temperature;
    private int humidity;
    private AirConditioner airConditioner;

    public House(AirConditioner airConditioner) {
        this.airConditioner = airConditioner;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
        if(temperature >= 30){
            airConditioner.switchOn();
        }
        else{
            airConditioner.switchOff();
        }
    }

    public double getTemperature() {
        return temperature;
    }

    public void setHumidity(int humidity){
        this.humidity = humidity;
    }

    public int getHumidity(){
        return humidity;
    }
}
