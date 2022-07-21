package org.mocuishla.myhouse.domain;

import org.junit.jupiter.api.Test;
import org.mocuishla.myhouse.adapters.fake.FakeActionRepository;
import org.mocuishla.myhouse.adapters.fake.FakeAirConditioner;
import org.mocuishla.myhouse.domain.ports.Action;
import org.mocuishla.myhouse.domain.ports.ActionRepository;
import org.mocuishla.myhouse.domain.ports.ActionType;
import org.mocuishla.myhouse.domain.ports.AirConditioner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class HouseTest {
    @Test
    public void shouldSetTemperature(){
        AirConditioner airConditioner = new FakeAirConditioner();
        ActionRepository actionRepository = new FakeActionRepository();
        House sut = new House(airConditioner, actionRepository);

        sut.setTemperature(36.3);
        double actualTemperature = sut.getTemperature();

        assertThat(actualTemperature).isEqualTo(36.3);
    }

    @Test
    public void shouldGetHumidity(){
        AirConditioner airConditioner = new FakeAirConditioner();
        ActionRepository actionRepository = new FakeActionRepository();
        House sut = new House(airConditioner, actionRepository);

        sut.setHumidity(40);
        int actualHumidity = sut.getHumidity();

        assertThat(actualHumidity).isEqualTo(40);
    }

    @Test
    public void shouldSwitchOnAirConditionerIfTempIsMoreThanThreshold(){
        AirConditioner airConditioner = new FakeAirConditioner();
        ActionRepository actionRepository = new FakeActionRepository();
        House sut = new House(airConditioner, actionRepository);

        sut.setTemperature(30);

        assertThat(airConditioner.isOn()).isTrue();
    }

    @Test
    public void shouldNotSwitchOnAirConditionerIfTempIsLessThanThreshold(){
        AirConditioner airConditioner = new FakeAirConditioner();
        ActionRepository actionRepository = new FakeActionRepository();
        House sut = new House(airConditioner, actionRepository);

        sut.setTemperature(25);

        assertThat(airConditioner.isOn()).isFalse();
    }

    @Test
    public void shouldSwitchOffAirConditionerIfTempChangeToLowerTempThanThreshold(){
        AirConditioner airConditioner = new FakeAirConditioner();
        ActionRepository actionRepository = new FakeActionRepository();
        House sut = new House(airConditioner, actionRepository);
        sut.setTemperature(34);

        sut.setTemperature(21);

        assertThat(airConditioner.isOn()).isFalse();
    }

    @Test
    public void shouldNotSwitchOffAirConditionerIfTempChangeToGreaterTempThanThreshold(){
        AirConditioner airConditioner = new FakeAirConditioner();
        ActionRepository actionRepository = new FakeActionRepository();
        House sut = new House(airConditioner, actionRepository);
        sut.setTemperature(34);

        sut.setTemperature(23);

        assertThat(airConditioner.isOn()).isTrue();
    }

    @Test
    public void shouldNotSwitchAirConditionerOnIfItIsAlreadyOn(){
        AirConditioner airConditioner = new FakeAirConditioner();
        ActionRepository actionRepository = new FakeActionRepository();
        House sut = new House(airConditioner, actionRepository);
        sut.setTemperature(34);

        assertThatCode(() -> sut.setTemperature(33)).doesNotThrowAnyException();
        assertThat(airConditioner.isOn()).isTrue();
    }

    @Test
    public void shouldSaveActionIntoRepositoryWhenACSwitchOn(){
        AirConditioner airConditioner = new FakeAirConditioner();
        ActionRepository actionRepository = new FakeActionRepository();
        House sut = new House(airConditioner, actionRepository);

        sut.setTemperature(34);

        List<Action> allActions = actionRepository.getAllActions();
        assertThat(allActions).hasSize(1);
        assertThat(allActions.get(0).getType()).isEqualTo(ActionType.TurnAirConditionerOn);
        assertThat(allActions.get(0).getTemperature()).isEqualTo(34);
    }

}
