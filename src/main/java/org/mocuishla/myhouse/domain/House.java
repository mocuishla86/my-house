package org.mocuishla.myhouse.domain;

public class House {

    private double temperature;
    private int humidity;

    public void setTemperature(double temperature) {
        this.temperature = temperature;
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
