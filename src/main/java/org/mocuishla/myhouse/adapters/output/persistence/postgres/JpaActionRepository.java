package org.mocuishla.myhouse.adapters.output.persistence.postgres;

import org.mocuishla.myhouse.domain.business.model.Action;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface JpaActionRepository extends CrudRepository<Action, UUID> {

}