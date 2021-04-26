package de.telekom.carrier.v1.api.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import de.telekom.carrier.v1.api.entity.Carrier;

import java.util.List;
import java.util.Optional;

public interface CarrierRepository extends CrudRepository<Carrier, Long>{

    // derived query
    List<Carrier> findAll();

    //@Query(value = "SELECT carrier FROM Carrier carrier WHERE carrier.name=:")
    List<Carrier> findAllByOrderByNameAsc();

    // jpql queries

    @Query(value = "SELECT carrier FROM Carrier carrier WHERE carrier.name=?1")
    List<Carrier> findByNameWithUnnamedParameter(String name);

    @Query(value = "SELECT carrier FROM Carrier carrier WHERE carrier.customerNumber =:customerNumber")
    Optional<Carrier> findByCostumerNumber(String customerNumber);
}
