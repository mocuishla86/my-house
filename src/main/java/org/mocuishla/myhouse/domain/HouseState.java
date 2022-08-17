package org.mocuishla.myhouse.domain;

import org.springframework.stereotype.Component;

@Component
public class HouseState {

    private double temperature;
    private int humidity;

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }




}
