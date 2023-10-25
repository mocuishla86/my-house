package org.mocuishla.myhouse.domain.ports;

public interface AirConditioner {
    void switchOnFreshAir();

    void switchOffFreshAir();

    void switchOnHumidifier();

    void switchOffHumidifier();

    boolean isFreshAirOn();

    boolean isHumidifierOn();

}
