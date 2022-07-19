package org.mocuishla.myhouse.adapters.fake;

import org.mocuishla.myhouse.domain.ports.AirConditioner;

public class FakeAirConditioner implements AirConditioner {

    private boolean state;

    @Override
    public void switchOn() {
        this.state = true;
    }

    @Override
    public void switchOff() {
        this.state = false;
    }

    @Override
    public boolean isOn() {
        return state;
    }
}
