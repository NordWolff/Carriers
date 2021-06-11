package de.telekom.carrier.v1.api.repository;

import org.springframework.data.repository.CrudRepository;

import de.telekom.carrier.v1.api.entity.ServiceNumber;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceNumberRepository extends CrudRepository<ServiceNumber, Long> {

    List<ServiceNumber> findAll();

}
