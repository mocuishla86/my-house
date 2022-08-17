package org.mocuishla.myhouse.domain;

import org.springframework.stereotype.Component;

public class PrintHouseStateListener implements HouseStateListener{
    @Override
    public void onStateChanged(HouseState newState){
        double temperature = newState.getTemperature();
        int humidity = newState.getHumidity();

        System.out.println("Current temperature " + temperature + " and current humidity " + humidity);
    }
}
