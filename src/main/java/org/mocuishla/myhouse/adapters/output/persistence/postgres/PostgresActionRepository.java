package org.mocuishla.myhouse.adapters.output.persistence.postgres;

import org.mocuishla.myhouse.domain.business.model.Action;
import org.mocuishla.myhouse.domain.business.model.ActionType;
import org.mocuishla.myhouse.domain.ports.ActionRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class PostgresActionRepository implements ActionRepository {

    private final JpaActionRepository jpaActionRepository;

    public PostgresActionRepository(JpaActionRepository jpaActionRepository) {
        this.jpaActionRepository = jpaActionRepository;
    }

    @Override
    public void saveAction(Action action) {
        ActionEntity actionEntity = new ActionEntity(
                action.getId(),
                action.getTimestamp(),
                action.getType().name(),
                action.getTemperature(),
                action.getHumidity());
        jpaActionRepository.save(actionEntity);
    }

    @Override
    public List<Action> getAllActions() {
        var iterable = jpaActionRepository.findAll();

        return StreamSupport
                .stream(iterable.spliterator(), false)
                .map(actionEntity -> new Action(
                        actionEntity.getId(),
                        actionEntity.getTimestamp(),
                        ActionType.valueOf(actionEntity.getType()),
                        actionEntity.getTemperature(),
                        actionEntity.getHumidity()))
                .collect(Collectors.toList());

    }

    @Override
    public List<Action> getAllActionsByType(ActionType actionType) {
        var iterable = jpaActionRepository.findByType(actionType.name());

        return StreamSupport
                .stream(iterable.spliterator(), false)
                .map(actionEntity -> new Action(
                        actionEntity.getId(),
                        actionEntity.getTimestamp(),
                        ActionType.valueOf(actionEntity.getType()),
                        actionEntity.getTemperature(),
                        actionEntity.getHumidity()))
                .collect(Collectors.toList());
    }
}
