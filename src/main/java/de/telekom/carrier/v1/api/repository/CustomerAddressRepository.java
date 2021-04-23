package de.telekom.carrier.v1.api.repository;

import de.telekom.carrier.v1.api.entity.Address;
import org.springframework.data.repository.CrudRepository;

import de.telekom.carrier.v1.api.entity.CustomerAddress;

public interface CustomerAddressRepository extends CrudRepository<CustomerAddress, Long>{

}
