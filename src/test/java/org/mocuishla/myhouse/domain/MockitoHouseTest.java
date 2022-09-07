package org.mocuishla.myhouse.domain;

import org.junit.jupiter.api.Test;
import org.mocuishla.myhouse.domain.business.House;
import org.mocuishla.myhouse.domain.business.listeners.FreshAirListener;
import org.mocuishla.myhouse.domain.business.listeners.HumidifierListener;
import org.mocuishla.myhouse.domain.business.listeners.PrintHouseStateListener;
import org.mocuishla.myhouse.domain.business.model.HouseState;
import org.mocuishla.myhouse.domain.ports.ActionRepository;
import org.mocuishla.myhouse.domain.ports.AirConditioner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class MockitoHouseTest {
    @Test
    public void shouldSetTemperature() {
        AirConditioner airConditioner = mock(AirConditioner.class);
        ActionRepository actionRepository = mock(ActionRepository.class);
        HouseState houseState = new HouseState();
        House sut = new House(houseState, actionRepository, List.of(
                new HumidifierListener(airConditioner, actionRepository),
                new FreshAirListener(airConditioner, actionRepository),
                new PrintHouseStateListener()));

        sut.setTemperature(36.3);
        double actualTemperature = sut.getTemperature();

        assertThat(actualTemperature).isEqualTo(36.3);
    }

    @Test
    public void shouldGetHumidity() {
        AirConditioner airConditioner = mock(AirConditioner.class);
        ActionRepository actionRepository = mock(ActionRepository.class);
        HouseState houseState = new HouseState();
        House sut = new House(houseState, actionRepository, List.of(
                new HumidifierListener(airConditioner, actionRepository),
                new FreshAirListener(airConditioner, actionRepository),
                new PrintHouseStateListener()));

        sut.setHumidity(40);
        int actualHumidity = sut.getHumidity();

        assertThat(actualHumidity).isEqualTo(40);
    }

    @Test
    public void shouldSwitchOnAirConditionerIfTempIsMoreThanThreshold() {
        AirConditioner airConditioner = mock(AirConditioner.class);
        ActionRepository actionRepository = mock(ActionRepository.class);
        HouseState houseState = new HouseState();
        House sut = new House(houseState, actionRepository, List.of(
                new HumidifierListener(airConditioner, actionRepository),
                new FreshAirListener(airConditioner, actionRepository),
                new PrintHouseStateListener()));

        sut.setTemperature(30);

        verify(airConditioner).switchOnFreshAir();
    }

    @Test
    public void shouldNotSwitchOnAirConditionerIfTempIsLessThanThreshold() {
        AirConditioner airConditioner = mock(AirConditioner.class);
        ActionRepository actionRepository = mock(ActionRepository.class);
        HouseState houseState = new HouseState();
        House sut = new House(houseState, actionRepository, List.of(
                new HumidifierListener(airConditioner, actionRepository),
                new FreshAirListener(airConditioner, actionRepository),
                new PrintHouseStateListener()));

        sut.setTemperature(25);

        verify(airConditioner, never()).switchOnFreshAir();
    }

    @Test
    public void shouldSwitchOffAirConditionerIfTempChangeToLowerTempThanThreshold() {
        AirConditioner airConditioner = mock(AirConditioner.class);
        ActionRepository actionRepository = mock(ActionRepository.class);
        HouseState houseState = new HouseState();
        House sut = new House(houseState, actionRepository, List.of(
                new HumidifierListener(airConditioner, actionRepository),
                new FreshAirListener(airConditioner, actionRepository),
                new PrintHouseStateListener()));
        when(airConditioner.isFreshAirOn()).thenReturn(true);

        sut.setTemperature(21);

        verify(airConditioner).switchOffFreshAir();

    }

    @Test
    public void shouldNotSwitchOffAirConditionerIfTempChangeToGreaterTempThanThreshold() {
        AirConditioner airConditioner = mock(AirConditioner.class);
        ActionRepository actionRepository = mock(ActionRepository.class);
        HouseState houseState = new HouseState();
        House sut = new House(houseState, actionRepository, List.of(
                new HumidifierListener(airConditioner, actionRepository),
                new FreshAirListener(airConditioner, actionRepository),
                new PrintHouseStateListener()));
        when(airConditioner.isFreshAirOn()).thenReturn(true);

        sut.setTemperature(23);

        verify(airConditioner, never()).switchOnFreshAir();
    }

    @Test
    public void shouldNotSwitchAirConditionerOnIfItIsAlreadyOn() {
        AirConditioner airConditioner = mock(AirConditioner.class);
        ActionRepository actionRepository = mock(ActionRepository.class);
        HouseState houseState = new HouseState();
        House sut = new House(houseState, actionRepository, List.of(
                new HumidifierListener(airConditioner, actionRepository),
                new FreshAirListener(airConditioner, actionRepository),
                new PrintHouseStateListener()));
        when(airConditioner.isFreshAirOn()).thenReturn(true);

        sut.setTemperature(48);

        verify(airConditioner, never()).switchOnFreshAir();
    }

    @Test
    public void shouldNotSwitchAirConditionerOffIfItIsAlreadyOff() {
        AirConditioner airConditioner = mock(AirConditioner.class);
        ActionRepository actionRepository = mock(ActionRepository.class);
        HouseState houseState = new HouseState();
        House sut = new House(houseState, actionRepository, List.of(
                new HumidifierListener(airConditioner, actionRepository),
                new FreshAirListener(airConditioner, actionRepository),
                new PrintHouseStateListener()));
        when(airConditioner.isFreshAirOn()).thenReturn(false);

        sut.setTemperature(10);

        verify(airConditioner, never()).switchOffFreshAir();
    }

    @Test
    public void shouldSaveActionIntoRepositoryWhenFreshAirSwitchOn() {
        AirConditioner airConditioner = mock(AirConditioner.class);
        ActionRepository actionRepository = mock(ActionRepository.class);
        HouseState houseState = new HouseState();
        House sut = new House(houseState, actionRepository, List.of(
                new HumidifierListener(airConditioner, actionRepository),
                new FreshAirListener(airConditioner, actionRepository),
                new PrintHouseStateListener()));
        when(airConditioner.isFreshAirOn()).thenReturn(false);
        sut.setHumidity(40);

        sut.setTemperature(34);

        verify(actionRepository).saveAction(any());
    }

    @Test
    public void shouldSaveActionIntoRepositoryWhenFreshAirSwitchOff() {
        AirConditioner airConditioner = mock(AirConditioner.class);
        ActionRepository actionRepository = mock(ActionRepository.class);
        HouseState houseState = new HouseState();
        House sut = new House(houseState, actionRepository, List.of(
                new HumidifierListener(airConditioner, actionRepository),
                new FreshAirListener(airConditioner, actionRepository),
                new PrintHouseStateListener()));
        when(airConditioner.isFreshAirOn()).thenReturn(false);
        sut.setHumidity(40);
        when(airConditioner.isFreshAirOn()).thenReturn(true);

        sut.setTemperature(20);

        verify(actionRepository).saveAction(any());
    }
}
