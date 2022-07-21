package org.mocuishla.myhouse.domain.ports;

import java.time.LocalDateTime;

public class Action {
    private LocalDateTime timestamp;
    private ActionType type;
    private double temperature;

    public Action(LocalDateTime timestamp, ActionType type, double temperature) {
        this.timestamp = timestamp;
        this.type = type;
        this.temperature = temperature;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public ActionType getType() {
        return type;
    }

    public double getTemperature() {
        return temperature;
    }

}
