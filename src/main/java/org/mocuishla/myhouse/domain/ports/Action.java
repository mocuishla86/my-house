package org.mocuishla.myhouse.domain.ports;

import java.time.LocalDateTime;

public class Action {
    private LocalDateTime timestamp;
    private ActionType type;
    private double temperature;
    private int humidity;

    public Action(LocalDateTime timestamp, ActionType type, double temperature, int humidity) {
        this.timestamp = timestamp;
        this.type = type;
        this.temperature = temperature;
        this.humidity = humidity;
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

    public int getHumidity() {
        return humidity;
    }

}
