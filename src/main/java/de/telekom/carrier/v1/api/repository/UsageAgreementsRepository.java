package de.telekom.carrier.v1.api.repository;

import org.springframework.data.repository.CrudRepository;

import de.telekom.carrier.v1.api.entity.UsageAgreement;

public interface UsageAgreementsRepository extends CrudRepository<UsageAgreement, Long> {

}
