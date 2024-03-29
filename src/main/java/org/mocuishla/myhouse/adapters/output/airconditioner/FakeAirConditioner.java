package org.mocuishla.myhouse.adapters.output.airconditioner;

import org.mocuishla.myhouse.domain.ports.AirConditioner;

public class FakeAirConditioner implements AirConditioner {
    private enum AirStatus {
        AirOn,
        AirOff,
    }

    private enum HumidifierStatus {
        HumidifierOn,
        HumidifierOff,
    }

    public enum LedStatus {
        LedOn,
        LedOff,
    }

    private AirStatus airStatus;
    private HumidifierStatus humidifierStatus;

    private LedStatus ledStatus;

    @Override
    public void switchOnFreshAir() {
        if (isFreshAirOn()) {
            throw new RuntimeException("Overheating");
        }
        this.airStatus = AirStatus.AirOn;
        this.ledStatus = LedStatus.LedOn;

    }

    @Override
    public void switchOffFreshAir() {
        if (!isFreshAirOn()) {
            throw new RuntimeException("Already off");
        }
        this.airStatus = AirStatus.AirOff;
        this.ledStatus = LedStatus.LedOff;
    }

    @Override
    public void switchOnHumidifier() {
        if (isHumidifierOn()) {
            throw new RuntimeException("Humidifier Already On");
        }
        this.humidifierStatus = HumidifierStatus.HumidifierOn;
    }

    @Override
    public void switchOffHumidifier() {
        if (!isHumidifierOn()) {
            throw new RuntimeException("Humidifier Already Off");
        }
        this.humidifierStatus = HumidifierStatus.HumidifierOff;
    }

    @Override
    public boolean isFreshAirOn() {
        return airStatus == AirStatus.AirOn;
    }

    @Override
    public boolean isHumidifierOn() {
        return humidifierStatus == HumidifierStatus.HumidifierOn;
    }

    public boolean isLedOn() {
        return ledStatus == LedStatus.LedOn;
    }

}
