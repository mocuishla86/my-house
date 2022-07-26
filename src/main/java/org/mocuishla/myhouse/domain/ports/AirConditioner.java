package org.mocuishla.myhouse.domain.ports;

public interface AirConditioner {
    void switchOn();
    void switchOff();
    void switchOnHumidifier();
    void switchOffHumidifier();
    boolean isOn();
}
