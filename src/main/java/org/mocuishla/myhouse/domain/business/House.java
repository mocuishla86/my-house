package org.mocuishla.myhouse.domain.business;

import org.mocuishla.myhouse.domain.business.listeners.HouseStateListener;
import org.mocuishla.myhouse.domain.business.model.Action;
import org.mocuishla.myhouse.domain.business.model.HouseState;
import org.mocuishla.myhouse.domain.ports.ActionRepository;

import java.util.List;

public class House {

    private HouseState houseState;
    private ActionRepository actionRepository;
    private List<HouseStateListener> houseStateListeners;

    public House(HouseState houseState, ActionRepository actionRepository, List<HouseStateListener> houseStateListeners) {
        this.houseState = houseState;
        this.actionRepository = actionRepository;
        this.houseStateListeners = houseStateListeners;
    }

    public void setTemperature(double temperature) {
        this.houseState.setTemperature(temperature);
        this.houseStateListeners.forEach(listener -> listener.onStateChanged(houseState));
    }

    public double getTemperature() {
        return houseState.getTemperature();
    }

    public void setHumidity(int humidity) {
        this.houseState.setHumidity(humidity);
        this.houseStateListeners.forEach(listener -> listener.onStateChanged(houseState));
    }

    public int getHumidity() {
        return houseState.getHumidity();
    }

    public List<Action> getAllActions() {
        return actionRepository.getAllActions();
    }
}
