package org.mocuishla.myhouse.domain.ports;

import org.mocuishla.myhouse.domain.business.model.Action;

import java.util.List;

public interface ActionRepository {
    void saveAction(Action action);

    List<Action> getAllActions();
}
