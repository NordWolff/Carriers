package de.telekom.carrier.v1.api.repository;

import org.springframework.data.repository.CrudRepository;

import de.telekom.carrier.v1.api.entity.OslAgreement;
import org.springframework.stereotype.Repository;

@Repository
public interface OslAgreementRepository extends CrudRepository<OslAgreement, Long> {

}
