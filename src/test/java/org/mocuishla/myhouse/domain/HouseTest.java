package org.mocuishla.myhouse.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mocuishla.myhouse.adapters.output.airconditioner.FakeAirConditioner;
import org.mocuishla.myhouse.adapters.output.persistence.fake.FakeActionRepository;
import org.mocuishla.myhouse.domain.business.House;
import org.mocuishla.myhouse.domain.business.listeners.FreshAirListener;
import org.mocuishla.myhouse.domain.business.listeners.HumidifierListener;
import org.mocuishla.myhouse.domain.business.listeners.PrintHouseStateListener;
import org.mocuishla.myhouse.domain.business.model.Action;
import org.mocuishla.myhouse.domain.business.model.ActionType;
import org.mocuishla.myhouse.domain.business.model.HouseState;
import org.mocuishla.myhouse.domain.ports.ActionRepository;
import org.mocuishla.myhouse.domain.ports.AirConditioner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class HouseTest {

    private AirConditioner airConditioner;
    private ActionRepository actionRepository;
    private House sut;

    @BeforeEach
    public void setUp() {
        airConditioner = new FakeAirConditioner();
        actionRepository = new FakeActionRepository();
        sut = new House(new HouseState(), actionRepository, List.of(
                new HumidifierListener(airConditioner, actionRepository),
                new FreshAirListener(airConditioner, actionRepository),
                new PrintHouseStateListener()));
    }

    @Test
    public void shouldSetTemperature() {
        sut.setTemperature(36.3);
        double actualTemperature = sut.getTemperature();

        assertThat(actualTemperature).isEqualTo(36.3);
    }

    @Test
    public void shouldGetHumidity() {
        sut.setHumidity(40);
        int actualHumidity = sut.getHumidity();

        assertThat(actualHumidity).isEqualTo(40);
    }

    @Test
    public void shouldSwitchOnAirConditionerIfTempIsMoreThanThreshold() {
        sut.setTemperature(30);

        assertThat(airConditioner.isFreshAirOn()).isTrue();
    }

    @Test
    public void shouldNotSwitchOnAirConditionerIfTempIsLessThanThreshold() {
        sut.setTemperature(25);

        assertThat(airConditioner.isFreshAirOn()).isFalse();
    }

    @Test
    public void shouldSwitchOffAirConditionerIfTempChangeToLowerTempThanThreshold() {
        sut.setTemperature(34);

        sut.setTemperature(21);

        assertThat(airConditioner.isFreshAirOn()).isFalse();
    }

    @Test
    public void shouldNotSwitchOffAirConditionerIfTempChangeToGreaterTempThanThreshold() {
        sut.setTemperature(34);

        sut.setTemperature(23);

        assertThat(airConditioner.isFreshAirOn()).isTrue();
    }

    @Test
    public void shouldNotSwitchAirConditionerOnIfItIsAlreadyOn() {
        sut.setTemperature(34);

        assertThatCode(() -> sut.setTemperature(33)).doesNotThrowAnyException();
        assertThat(airConditioner.isFreshAirOn()).isTrue();
    }

    @Test
    public void shouldNotSwitchAirConditionerOffIfItIsAlreadyOff() {
        sut.setTemperature(12);

        assertThatCode(() -> sut.setTemperature(13)).doesNotThrowAnyException();
        assertThat(airConditioner.isFreshAirOn()).isFalse();
    }

    @Test
    public void shouldSaveActionIntoRepositoryWhenFreshAirSwitchOn() {
        sut.setHumidity(40);

        sut.setTemperature(34);

        List<Action> allActions = sut.getAllActions();
        assertThat(allActions).hasSize(1);
        Action firstAction = allActions.get(0);
        assertThat(firstAction.getType()).isEqualTo(ActionType.TurnFreshAirOn);
        assertThat(firstAction.getTemperature()).isEqualTo(34);
    }

    @Test
    public void shouldSaveActionIntoRepositoryWhenFreshAirSwitchOff() {
        sut.setHumidity(40);

        sut.setTemperature(34);
        sut.setTemperature(20);

        List<Action> allActions = sut.getAllActions();
        assertThat(allActions).hasSize(2);
        Action secondAction = allActions.get(1);
        assertThat(secondAction.getType()).isEqualTo(ActionType.TurnFreshAirOff);
        assertThat(secondAction.getTemperature()).isEqualTo(20);
    }

    @Test
    public void shouldSwitchOnHumidifierWhenHumidityIsUnder30() {
        sut.setHumidity(29);

        assertThat(airConditioner.isHumidifierOn()).isTrue();
    }

    @Test
    public void shouldSwitchOffHumidifierWhenHumidityIsAbove30() {
        sut.setHumidity(29);

        sut.setHumidity(31);

        assertThat(airConditioner.isHumidifierOn()).isFalse();
    }

    @Test
    public void shouldNotSwitchHumidifierOnIfItIsAlreadyOn() {
        sut.setHumidity(26);

        assertThatCode(() -> sut.setHumidity(28)).doesNotThrowAnyException();
        assertThat(airConditioner.isHumidifierOn()).isTrue();
    }

    @Test
    public void shouldNotSwitchHumidifierOffIfItIsAlreadyOff() {
        sut.setHumidity(34);

        assertThatCode(() -> sut.setHumidity(33)).doesNotThrowAnyException();
        assertThat(airConditioner.isHumidifierOn()).isFalse();
    }

    @Test
    public void shouldSaveActionIntoRepositoryWhenHumidifierSwitchOn() {
        sut.setHumidity(27);

        List<Action> allActions = sut.getAllActions();
        assertThat(allActions).hasSize(1);
        Action firstAction = allActions.get(0);
        assertThat(firstAction.getType()).isEqualTo(ActionType.TurnHumidifierOn);
        assertThat(firstAction.getHumidity()).isEqualTo(27);
    }

    @Test
    public void shouldSaveActionIntoRepositoryWhenHumidifierSwitchOff() {
        sut.setHumidity(27);
        sut.setHumidity(31);

        List<Action> allActions = sut.getAllActions();
        assertThat(allActions).hasSize(2);
        Action secondAction = allActions.get(1);
        assertThat(secondAction.getType()).isEqualTo(ActionType.TurnHumidifierOff);
        assertThat(secondAction.getHumidity()).isEqualTo(31);
    }

}
