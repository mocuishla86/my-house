package org.mocuishla.myhouse.adapters.fake;

import org.mocuishla.myhouse.domain.ports.AirConditioner;

public class FakeAirConditioner implements AirConditioner {
    private enum Status{
        On,
        Off,
    }

    private Status state;

    @Override
    public void switchOn() {
        this.state = Status.On;
    }

    @Override
    public void switchOff() {
        this.state = Status.Off;
    }

    @Override
    public boolean isOn() {
        return state == Status.On;
    }
}
