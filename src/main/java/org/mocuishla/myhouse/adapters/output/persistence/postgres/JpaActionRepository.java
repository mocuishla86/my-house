package org.mocuishla.myhouse.adapters.output.persistence.postgres;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface JpaActionRepository extends CrudRepository<ActionEntity, UUID> {

}