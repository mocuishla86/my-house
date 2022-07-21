package org.mocuishla.myhouse.adapters.fake;

import org.mocuishla.myhouse.domain.ports.Action;
import org.mocuishla.myhouse.domain.ports.ActionRepository;

import java.util.ArrayList;
import java.util.List;

public class FakeActionRepository implements ActionRepository {

    private List<Action> actions = new ArrayList<>();

    @Override
    public void saveAction(Action action) {
        actions.add(action);
    }

    @Override
    public List<Action> getAllActions() {
        return actions;
    }
}