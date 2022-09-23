package org.mocuishla.myhouse.adapters.output.persistence.postgres;

import org.junit.jupiter.api.Test;
import org.mocuishla.myhouse.domain.business.model.Action;
import org.mocuishla.myhouse.domain.business.model.ActionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PostgresActionRepositoryTest {

    @Autowired
    private JpaActionRepository jpaActionRepository;

    @Test
    void shouldSaveAction() {
        PostgresActionRepository sut = new PostgresActionRepository(jpaActionRepository);
        Action action = new Action(LocalDateTime.now(), ActionType.TurnFreshAirOn, 44, 34);

        sut.saveAction(action);
        List<Action> actions = sut.getAllActions();

        assertThat(actions).anyMatch(actionInList ->
                actionInList.getId().equals(action.getId())
                        && actionInList.getTimestamp().equals(action.getTimestamp())
                        && actionInList.getTemperature() == 44
                        && actionInList.getHumidity() == 34
                        && actionInList.getType().equals(ActionType.TurnFreshAirOn));
    }

    @Test
    void shouldSaveActionByType() {
        PostgresActionRepository sut = new PostgresActionRepository(jpaActionRepository);
        Action action = new Action(LocalDateTime.now(), ActionType.TurnFreshAirOn, 44, 34);
        Action action2 = new Action(LocalDateTime.now(), ActionType.TurnFreshAirOff, 24, 34);

        sut.saveAction(action);
        sut.saveAction(action2);

        List<Action> actions = sut.getAllActionsByType(ActionType.TurnFreshAirOn);

        assertThat(actions).anyMatch(actionInList ->
                actionInList.getId().equals(action.getId())
                        && actionInList.getTimestamp().equals(action.getTimestamp())
                        && actionInList.getTemperature() == 44
                        && actionInList.getHumidity() == 34
                        && actionInList.getType().equals(ActionType.TurnFreshAirOn));
        assertThat(actions).allMatch(actionInList ->
                actionInList.getType().equals(ActionType.TurnFreshAirOn)
        );
    }

    @Test
    void shouldSaveActionByTemperature() {
        PostgresActionRepository sut = new PostgresActionRepository(jpaActionRepository);
        Action action = new Action(LocalDateTime.now(), ActionType.TurnFreshAirOn, 44, 34);

        sut.saveAction(action);

        List<Action> actions = sut.getAllActionsByTemperature(44);

        assertThat(actions).anyMatch(actionInList ->
                actionInList.getId().equals(action.getId())
                        && actionInList.getTimestamp().equals(action.getTimestamp())
                        && actionInList.getTemperature() == 44
                        && actionInList.getHumidity() == 34
                        && actionInList.getType().equals(ActionType.TurnFreshAirOn));

    }

}