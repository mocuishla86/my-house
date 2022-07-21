package org.mocuishla.myhouse.adapters.fake;

import org.junit.jupiter.api.Test;
import org.mocuishla.myhouse.domain.ports.Action;
import org.mocuishla.myhouse.domain.ports.ActionType;

import java.time.LocalDateTime;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class FakeActionRepositoryTest {

    @Test
    public void shouldSaveAction() {
        FakeActionRepository sut = new FakeActionRepository();
        Action action = new Action(LocalDateTime.now(), ActionType.TurnAirConditionerOff, 33);

        sut.saveAction(action);
        List<Action> actions = sut.getAllActions();

        assertThat(actions).hasSize(1).contains(action);
    }

    @Test
    public void shouldHaveNoActionAtTheBeginning() {
        FakeActionRepository sut = new FakeActionRepository();

        List<Action> actions = sut.getAllActions();

        assertThat(actions).isEmpty();
    }
}