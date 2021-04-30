package de.telekom.carrier.v1.api.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import de.telekom.carrier.v1.api.entity.OslAgreement;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OslAgreementRepository extends CrudRepository<OslAgreement, Long> {

    List<OslAgreement> findAll();

    @Query("SELECT s FROM OslAgreement s JOIN s.usedHardware t WHERE t = LOWER(:hardware)")
    List<OslAgreement> retrieveByHardware(@Param("hardware") String hardware);

}
