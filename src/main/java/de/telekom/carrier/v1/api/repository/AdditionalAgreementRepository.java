package de.telekom.carrier.v1.api.repository;

import org.springframework.data.repository.CrudRepository;

import de.telekom.carrier.v1.api.entity.AdditionalAgreement;

import java.util.List;

public interface AdditionalAgreementRepository extends CrudRepository<AdditionalAgreement, Long>{

    List<AdditionalAgreement> findAll();

}
