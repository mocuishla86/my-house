package org.mocuishla.myhouse.domain;

import org.junit.jupiter.api.Test;
import org.mocuishla.myhouse.adapters.fake.FakeAirConditioner;
import org.mocuishla.myhouse.domain.ports.AirConditioner;

import static org.assertj.core.api.Assertions.assertThat;

public class HouseTest {
    @Test
    public void shouldSetTemperature(){
        AirConditioner airConditioner = new FakeAirConditioner();
        House sut = new House(airConditioner);

        sut.setTemperature(36.3);
        double actualTemperature = sut.getTemperature();

        assertThat(actualTemperature).isEqualTo(36.3);
    }

    @Test
    public void shouldGetHumidity(){
        AirConditioner airConditioner = new FakeAirConditioner();
        House sut = new House(airConditioner);

        sut.setHumidity(40);
        int actualHumidity = sut.getHumidity();

        assertThat(actualHumidity).isEqualTo(40);
    }
}
