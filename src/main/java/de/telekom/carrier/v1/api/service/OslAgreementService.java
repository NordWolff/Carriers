package de.telekom.carrier.v1.api.service;

import de.telekom.carrier.v1.api.entity.OslAgreement;
import de.telekom.carrier.v1.api.repository.OslAgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class OslAgreementService {

    @Autowired
    OslAgreementRepository oslAgreementRepository;

    public List<OslAgreement> findAll() {
        return oslAgreementRepository.findAll();
    }

    @Transactional
    public OslAgreement save(OslAgreement agreement) {
       return oslAgreementRepository.save(agreement);
    }

    public Optional<OslAgreement> findById(Long agreementsId) {
        return oslAgreementRepository.findById(agreementsId);
    }

    public void update(OslAgreement oslAgreement) {
        oslAgreementRepository.save(oslAgreement);
    }

    public void deleteById(Long agreementsId) {
        oslAgreementRepository.deleteById(agreementsId);
    }

    public List<OslAgreement> retrieveByHardware(String hardwareName){
      return oslAgreementRepository.retrieveByHardware(hardwareName);
    }

    public void deleteAll() {
        oslAgreementRepository.deleteAll();
    }
}
