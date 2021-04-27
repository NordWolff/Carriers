package de.telekom.carrier.v1.api.repository;

import org.springframework.data.repository.CrudRepository;

import de.telekom.carrier.v1.api.entity.BillAddress;
import org.springframework.stereotype.Repository;

@Repository
public interface BillAddressRepository extends CrudRepository<BillAddress, Long> {

}
