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
        if(isOn()){
            throw new RuntimeException("Overheating");
        }
        this.state = Status.On;
    }

    @Override
    public void switchOff() {
        if(!isOn()){
            throw new RuntimeException("Already off");
        }
        this.state = Status.Off;
    }

    @Override
    public void switchOnHumidifier(){
        this.state = Status.On;
    }

    @Override
    public boolean isOn() {
        return state == Status.On;
    }
}
