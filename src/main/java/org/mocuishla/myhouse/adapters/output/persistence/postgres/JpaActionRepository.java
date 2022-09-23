package org.mocuishla.myhouse.adapters.output.persistence.postgres;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface JpaActionRepository extends CrudRepository<ActionEntity, UUID> {
 List<ActionEntity> findByType(String actionType);

 List<ActionEntity> findByTemperature(double temperature);
}