package org.mocuishla.myhouse.domain.ports;

import org.mocuishla.myhouse.domain.business.model.Action;
import org.mocuishla.myhouse.domain.business.model.ActionType;

import java.util.List;

public interface ActionRepository {
    void saveAction(Action action);

    List<Action> getAllActions();

    List<Action> getAllActionsByType(ActionType actionType);
}
