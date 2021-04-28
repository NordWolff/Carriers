package de.telekom.carrier.v1.api.repository;

import org.springframework.data.repository.CrudRepository;

import de.telekom.carrier.v1.api.entity.OslAgreement;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Repository
public interface OslAgreementRepository extends CrudRepository<OslAgreement, Long> {

    List<OslAgreement> findAll();

}
