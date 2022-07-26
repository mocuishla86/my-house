package org.mocuishla.myhouse.adapters.fake;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class FakeAirConditionerTest {

    @Test
    public void shoulBeOffAtFirst() {
        FakeAirConditioner sut = new FakeAirConditioner();

        boolean actualIsOn = sut.isOn();

        assertThat(actualIsOn).isFalse();
    }

    @Test
    public void shouldTurnOn() {
        FakeAirConditioner sut = new FakeAirConditioner();

        sut.switchOn();

        assertThat(sut.isOn()).isTrue();
    }

    @Test
    public void shouldTurnOff() {
        FakeAirConditioner sut = new FakeAirConditioner();
        sut.switchOn();

        sut.switchOff();

        assertThat(sut.isOn()).isFalse();
    }

    @Test
    public void shouldFailIfSwitchingOnWhenAlreadyOn() {
        FakeAirConditioner sut = new FakeAirConditioner();
        sut.switchOn();

        assertThatThrownBy(() -> sut.switchOn()).hasMessage("Overheating");
    }

    @Test
    public void shouldFailIfSwitchingOffWhenAlreadyOff() {
        FakeAirConditioner sut = new FakeAirConditioner();

        assertThatThrownBy(() -> sut.switchOff()).hasMessage("Already off");
    }

    @Test
    public void shouldSwitchOnHumidifier(){
        FakeAirConditioner sut = new FakeAirConditioner();
        sut.switchOn();

        sut.switchOnHumidifier();

        assertThat(sut.isOn()).isTrue();
    }

    @Test
    public void shouldSwitchOffHumidifier(){
        FakeAirConditioner sut = new FakeAirConditioner();
        sut.switchOn();

        sut.switchOffHumidifier();

        assertThat(sut.isOn()).isFalse();
    }

}