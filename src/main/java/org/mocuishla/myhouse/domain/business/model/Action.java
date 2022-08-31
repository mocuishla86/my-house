package org.mocuishla.myhouse.domain.business.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Action {
    private LocalDateTime timestamp;
    private ActionType type;
    private double temperature;
    private int humidity;

    private UUID id;

    public Action(LocalDateTime timestamp, ActionType type, double temperature, int humidity) {
        this.timestamp = timestamp;
        this.type = type;
        this.temperature = temperature;
        this.humidity = humidity;
        this.id = UUID.randomUUID();
    }

    public Action(UUID id, LocalDateTime timestamp, ActionType type, double temperature, int humidity) {
        this.timestamp = timestamp;
        this.type = type;
        this.temperature = temperature;
        this.humidity = humidity;
        this.id = id;
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}
