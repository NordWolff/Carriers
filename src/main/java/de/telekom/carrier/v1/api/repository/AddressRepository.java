package de.telekom.carrier.v1.api.repository;

import de.telekom.carrier.v1.api.entity.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address,Long> {
}
