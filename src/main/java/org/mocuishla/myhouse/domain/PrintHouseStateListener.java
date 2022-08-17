package org.mocuishla.myhouse.domain;

public class PrintHouseStateListener implements HouseStateListener {
    @Override
    public void onStateChanged(HouseState newState) {
        double temperature = newState.getTemperature();
        int humidity = newState.getHumidity();

        System.out.println("Current temperature " + temperature + " and current humidity " + humidity);
    }
}
