package org.mocuishla.myhouse.adapters.output.persistence.postgres;

import org.mocuishla.myhouse.domain.business.model.Action;
import org.mocuishla.myhouse.domain.business.model.ActionType;
import org.mocuishla.myhouse.domain.ports.ActionRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
        Stream<ActionEntity> actionEntityStream = StreamSupport.stream(iterable.spliterator(), false);

        return toDomainActionList(actionEntityStream);

    }

    @Override
    public List<Action> getAllActionsByType(ActionType actionType) {
        var actionEntities = jpaActionRepository.findByType(actionType.name());
        Stream<ActionEntity> actionEntityStream = actionEntities.stream();

        return toDomainActionList(actionEntityStream);
    }

    @Override
    public List<Action> getAllActionsByTemperature(double temperature) {
        var actionEntities = jpaActionRepository.findByTemperature(temperature);
        Stream<ActionEntity> stream = actionEntities.stream();

        return toDomainActionList(stream);
    }

    private static List<Action> toDomainActionList(Stream<ActionEntity> actionEntityStream) {
        return actionEntityStream
                .map(actionEntity -> new Action(
                        actionEntity.getId(),
                        actionEntity.getTimestamp(),
                        ActionType.valueOf(actionEntity.getType()),
                        actionEntity.getTemperature(),
                        actionEntity.getHumidity()))
                .collect(Collectors.toList());
    }

}
