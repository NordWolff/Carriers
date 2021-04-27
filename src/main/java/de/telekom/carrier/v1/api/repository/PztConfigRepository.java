package de.telekom.carrier.v1.api.repository;

import org.springframework.data.repository.CrudRepository;

import de.telekom.carrier.v1.api.entity.PztConfig;
import org.springframework.stereotype.Repository;

@Repository
public interface PztConfigRepository extends CrudRepository<PztConfig, Long> {

}
