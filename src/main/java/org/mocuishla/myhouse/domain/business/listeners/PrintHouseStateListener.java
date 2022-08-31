package org.mocuishla.myhouse.domain.business.listeners;

import org.mocuishla.myhouse.domain.business.model.HouseState;

public class PrintHouseStateListener implements HouseStateListener {
    @Override
    public void onStateChanged(HouseState newState) {
        double temperature = newState.getTemperature();
        int humidity = newState.getHumidity();

        System.out.println("Current temperature " + temperature + " and current humidity " + humidity);
    }
}
