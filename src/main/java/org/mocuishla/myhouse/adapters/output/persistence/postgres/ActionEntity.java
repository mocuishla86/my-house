package org.mocuishla.myhouse.adapters.output.persistence.postgres;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "action")
public class ActionEntity {
    private LocalDateTime timestamp;
    private String type;
    private double temperature;
    private int humidity;

    @Id
    private UUID id;

    public ActionEntity(UUID id, LocalDateTime timestamp, String type, double temperature, int humidity) {
        this.timestamp = timestamp;
        this.type = type;
        this.temperature = temperature;
        this.humidity = humidity;
        this.id = id;
    }

    public ActionEntity() {
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getType() {
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


}
