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

        assertThat(airConditioner.isFreshAirOn()).isTrue();
    }

    @Test
    public void shouldNotSwitchOnAirConditionerIfTempIsLessThanThreshold(){
        AirConditioner airConditioner = new FakeAirConditioner();
        ActionRepository actionRepository = new FakeActionRepository();
        House sut = new House(airConditioner, actionRepository);

        sut.setTemperature(25);

        assertThat(airConditioner.isFreshAirOn()).isFalse();
    }

    @Test
    public void shouldSwitchOffAirConditionerIfTempChangeToLowerTempThanThreshold(){
        AirConditioner airConditioner = new FakeAirConditioner();
        ActionRepository actionRepository = new FakeActionRepository();
        House sut = new House(airConditioner, actionRepository);
        sut.setTemperature(34);

        sut.setTemperature(21);

        assertThat(airConditioner.isFreshAirOn()).isFalse();
    }

    @Test
    public void shouldNotSwitchOffAirConditionerIfTempChangeToGreaterTempThanThreshold(){
        AirConditioner airConditioner = new FakeAirConditioner();
        ActionRepository actionRepository = new FakeActionRepository();
        House sut = new House(airConditioner, actionRepository);
        sut.setTemperature(34);

        sut.setTemperature(23);

        assertThat(airConditioner.isFreshAirOn()).isTrue();
    }

    @Test
    public void shouldNotSwitchAirConditionerOnIfItIsAlreadyOn(){
        AirConditioner airConditioner = new FakeAirConditioner();
        ActionRepository actionRepository = new FakeActionRepository();
        House sut = new House(airConditioner, actionRepository);
        sut.setTemperature(34);

        assertThatCode(() -> sut.setTemperature(33)).doesNotThrowAnyException();
        assertThat(airConditioner.isFreshAirOn()).isTrue();
    }

    @Test
    public void shouldNotSwitchAirConditionerOffIfItIsAlreadyOff(){
        AirConditioner airConditioner = new FakeAirConditioner();
        ActionRepository actionRepository = new FakeActionRepository();
        House sut = new House(airConditioner, actionRepository);
        sut.setTemperature(12);

        assertThatCode(() -> sut.setTemperature(13)).doesNotThrowAnyException();
        assertThat(airConditioner.isFreshAirOn()).isFalse();
    }

    @Test
    public void shouldSaveActionIntoRepositoryWhenACSwitchOn(){
        AirConditioner airConditioner = new FakeAirConditioner();
        ActionRepository actionRepository = new FakeActionRepository();
        House sut = new House(airConditioner, actionRepository);

        sut.setTemperature(34);

        List<Action> allActions = sut.getAllActions();
        assertThat(allActions).hasSize(1);
        Action firstAction = allActions.get(0);
        assertThat(firstAction.getType()).isEqualTo(ActionType.TurnAirConditionerOn);
        assertThat(firstAction.getTemperature()).isEqualTo(34);
    }

    @Test
    public void shouldSaveActionIntoRepositoryWhenACSwitchOff(){
        AirConditioner airConditioner = new FakeAirConditioner();
        ActionRepository actionRepository = new FakeActionRepository();
        House sut = new House(airConditioner, actionRepository);

        sut.setTemperature(34);
        sut.setTemperature(20);

        List<Action> allActions = sut.getAllActions();
        assertThat(allActions).hasSize(2);
        Action secondAction = allActions.get(1);
        assertThat(secondAction.getType()).isEqualTo(ActionType.TurnAirConditionerOff);
        assertThat(secondAction.getTemperature()).isEqualTo(20);
    }

    @Test
    public void shouldSwitchOnHumidifierWhenHumidityIsUnder30(){
        AirConditioner airConditioner = new FakeAirConditioner();
        ActionRepository actionRepository = new FakeActionRepository();
        House sut = new House(airConditioner, actionRepository);

        sut.setHumidity(29);

        assertThat(airConditioner.isHumidifierOn()).isTrue();
    }

    @Test
    public void shouldSwitchOffHumidifierWhenHumidityIsAbove30(){
        AirConditioner airConditioner = new FakeAirConditioner();
        ActionRepository actionRepository = new FakeActionRepository();
        House sut = new House(airConditioner, actionRepository);
        sut.setHumidity(29);

        sut.setHumidity(31);

        assertThat(airConditioner.isHumidifierOn()).isFalse();
    }

    @Test
    public void shouldNotSwitchHumidifierOnIfItIsAlreadyOn(){
        AirConditioner airConditioner = new FakeAirConditioner();
        ActionRepository actionRepository = new FakeActionRepository();
        House sut = new House(airConditioner, actionRepository);
        sut.setHumidity(26);

        assertThatCode(() -> sut.setHumidity(28)).doesNotThrowAnyException();
        assertThat(airConditioner.isHumidifierOn()).isTrue();
    }

    @Test
    public void shouldNotSwitchHumidifierOffIfItIsAlreadyOff(){
        AirConditioner airConditioner = new FakeAirConditioner();
        ActionRepository actionRepository = new FakeActionRepository();
        House sut = new House(airConditioner, actionRepository);
        sut.setHumidity(34);

        assertThatCode(() -> sut.setHumidity(33)).doesNotThrowAnyException();
        assertThat(airConditioner.isHumidifierOn()).isFalse();
    }


}
