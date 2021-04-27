package de.telekom.carrier.v1.api.repository;

import org.springframework.data.repository.CrudRepository;

import de.telekom.carrier.v1.api.entity.UsageAgreement;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsageAgreementsRepository extends CrudRepository<UsageAgreement, Long> {

    List<UsageAgreement> findAll();

}
