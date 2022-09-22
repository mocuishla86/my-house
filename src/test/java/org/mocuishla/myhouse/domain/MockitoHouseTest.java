package org.mocuishla.myhouse.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mocuishla.myhouse.domain.business.House;
import org.mocuishla.myhouse.domain.business.listeners.FreshAirListener;
import org.mocuishla.myhouse.domain.business.listeners.HumidifierListener;
import org.mocuishla.myhouse.domain.business.listeners.PrintHouseStateListener;
import org.mocuishla.myhouse.domain.business.model.Action;
import org.mocuishla.myhouse.domain.business.model.ActionType;
import org.mocuishla.myhouse.domain.business.model.HouseState;
import org.mocuishla.myhouse.domain.ports.ActionRepository;
import org.mocuishla.myhouse.domain.ports.AirConditioner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class MockitoHouseTest {

    private AirConditioner airConditioner;
    private ActionRepository actionRepository;
    private House sut;

    @BeforeEach
    public void setUp() {
        airConditioner = mock(AirConditioner.class);
        actionRepository = mock(ActionRepository.class);
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

        verify(airConditioner).switchOnFreshAir();
    }

    @Test
    public void shouldNotSwitchOnAirConditionerIfTempIsLessThanThreshold() {
        sut.setTemperature(25);

        verify(airConditioner, never()).switchOnFreshAir();
    }

    @Test
    public void shouldSwitchOffAirConditionerIfTempChangeToLowerTempThanThreshold() {
        when(airConditioner.isFreshAirOn()).thenReturn(true);

        sut.setTemperature(21);

        verify(airConditioner).switchOffFreshAir();

    }

    @Test
    public void shouldNotSwitchOffAirConditionerIfTempChangeToGreaterTempThanThreshold() {
        when(airConditioner.isFreshAirOn()).thenReturn(true);

        sut.setTemperature(23);

        verify(airConditioner, never()).switchOnFreshAir();
    }

    @Test
    public void shouldNotSwitchAirConditionerOnIfItIsAlreadyOn() {
        when(airConditioner.isFreshAirOn()).thenReturn(true);

        sut.setTemperature(48);

        verify(airConditioner, never()).switchOnFreshAir();
    }

    @Test
    public void shouldNotSwitchAirConditionerOffIfItIsAlreadyOff() {
        when(airConditioner.isFreshAirOn()).thenReturn(false);

        sut.setTemperature(10);

        verify(airConditioner, never()).switchOffFreshAir();
    }

    @Test
    public void shouldSaveActionIntoRepositoryWhenFreshAirSwitchOn() {
        when(airConditioner.isFreshAirOn()).thenReturn(false);
        sut.setHumidity(40);

        sut.setTemperature(34);

        verify(actionRepository).saveAction(any());
    }

    @Test
    public void shouldSaveActionIntoRepositoryWhenFreshAirSwitchOff() {
        when(airConditioner.isFreshAirOn()).thenReturn(false);
        sut.setHumidity(40);
        when(airConditioner.isFreshAirOn()).thenReturn(true);

        sut.setTemperature(20);

        verify(actionRepository).saveAction(any());
    }

    @Test
    public void shouldSwitchOnHumidifierWhenHumidityIsUnder30() {
        sut.setHumidity(29);

        verify(airConditioner).switchOnHumidifier();
    }

    @Test
    public void shouldSwitchOffHumidifierWhenHumidityIsAbove30() {
        when(airConditioner.isHumidifierOn()).thenReturn(true);

        sut.setHumidity(31);

        verify(airConditioner).switchOffHumidifier();
    }

    @Test
    public void shouldNotSwitchHumidifierOnIfItIsAlreadyOn() {
        when(airConditioner.isHumidifierOn()).thenReturn(true);

        sut.setHumidity(29);

        verify(airConditioner, never()).switchOnHumidifier();
    }

    @Test
    public void shouldNotSwitchHumidifierOffIfItIsAlreadyOff() {
        when(airConditioner.isHumidifierOn()).thenReturn(false);

        sut.setHumidity(34);

        verify(airConditioner, never()).switchOffHumidifier();
    }

    @Test
    public void shouldSaveActionIntoRepositoryWhenHumidifierSwitchOn() {
        when(airConditioner.isHumidifierOn()).thenReturn(false);

        sut.setHumidity(15);

        verify(actionRepository).saveAction(argThat(action -> action.getHumidity() == 15 && action.getType() == ActionType.TurnHumidifierOn));
    }

    @Test
    public void shouldSaveActionIntoRepositoryWhenHumidifierSwitchOff() {
        when(airConditioner.isHumidifierOn()).thenReturn(true);

        sut.setHumidity(31);

        verify(actionRepository).saveAction(argThat(action -> action.getHumidity() == 31 && action.getType() == ActionType.TurnHumidifierOff));
    }

    @Test
    public void shouldGetAllActions() {
        List<Action> actions = List.of(new Action(UUID.randomUUID(), LocalDateTime.now(), ActionType.TurnFreshAirOn, 42, 23));
        when(actionRepository.getAllActions()).thenReturn(actions);

        List<Action> actualActions = sut.getAllActions();

        assertThat(actualActions).isEqualTo(actions);
    }

    @Test
    public void shouldGetAllActionsByType() {
        List<Action> actions = List.of(new Action(UUID.randomUUID(), LocalDateTime.now(), ActionType.TurnFreshAirOn, 42, 23));
        when(actionRepository.getAllActionsByType(ActionType.TurnFreshAirOn)).thenReturn(actions);

        List<Action> actualActions = sut.getAllActionsByType(ActionType.TurnFreshAirOn);

        assertThat(actualActions).isEqualTo(actions);
    }
}
