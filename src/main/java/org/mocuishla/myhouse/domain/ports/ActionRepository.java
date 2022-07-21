package org.mocuishla.myhouse.domain.ports;

import java.time.LocalDateTime;
import java.util.List;

public interface ActionRepository {
    void saveAction(Action action);
    List<Action> getAllActions();
}
