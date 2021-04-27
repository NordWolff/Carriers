package de.telekom.carrier.v1.api.repository;

import org.springframework.data.repository.CrudRepository;

import de.telekom.carrier.v1.api.entity.CustomerAddress;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerAddressRepository extends CrudRepository<CustomerAddress, Long>{

}
