package org.mocuishla.myhouse.adapters;

import org.mocuishla.myhouse.domain.ports.Action;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface JpaActionRepository extends CrudRepository<Action, UUID> {

}