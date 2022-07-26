package org.mocuishla.myhouse.adapters.fake;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FakeAirConditionerTest {

    @Test
    public void shouldBeOffAtFirst() {
        FakeAirConditioner sut = new FakeAirConditioner();

        boolean actualFreshAirIsOn = sut.isFreshAirOn();
        boolean actualHumidityIsOn = sut.isHumidifierOn();

        assertThat(actualFreshAirIsOn).isFalse();
        assertThat(actualHumidityIsOn).isFalse();
    }

    @Test
    public void shouldTurnOnFreshAir() {
        FakeAirConditioner sut = new FakeAirConditioner();

        sut.switchOnFreshAir();

        assertThat(sut.isFreshAirOn()).isTrue();
    }

    @Test
    public void shouldTurnOffFreshAir() {
        FakeAirConditioner sut = new FakeAirConditioner();
        sut.switchOnFreshAir();

        sut.switchOffFreshAir();

        assertThat(sut.isFreshAirOn()).isFalse();
    }

    @Test
    public void shouldFailIfSwitchingOnWhenAlreadyOn() {
        FakeAirConditioner sut = new FakeAirConditioner();
        sut.switchOnFreshAir();

        assertThatThrownBy(() -> sut.switchOnFreshAir()).hasMessage("Overheating");
    }

    @Test
    public void shouldFailIfSwitchingOffWhenAlreadyOff() {
        FakeAirConditioner sut = new FakeAirConditioner();

        assertThatThrownBy(() -> sut.switchOffFreshAir()).hasMessage("Already off");
    }

    @Test
    public void shouldSwitchOnHumidifier(){
        FakeAirConditioner sut = new FakeAirConditioner();

        sut.switchOnHumidifier();

        assertThat(sut.isHumidifierOn()).isTrue();
    }

    @Test
    public void shouldSwitchOffHumidifier(){
        FakeAirConditioner sut = new FakeAirConditioner();
        sut.switchOnHumidifier();

        sut.switchOffHumidifier();

        assertThat(sut.isHumidifierOn()).isFalse();
    }

    @Test
    public void checkIfFreshAirIsStillOnIfWeSwitchOnTheHumidifier(){
        FakeAirConditioner sut = new FakeAirConditioner();
        sut.switchOnFreshAir();
        sut.switchOnHumidifier();

        assertThat(sut.isFreshAirOn()).isTrue();
    }
}