package de.telekom.carrier.v1.api.service;

import de.telekom.carrier.v1.api.entity.AdditionalAgreement;
import de.telekom.carrier.v1.api.repository.AdditionalAgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AdditionalAgreementService {

    @Autowired
    AdditionalAgreementRepository additionalAgreementRepository;

    @Transactional
    public AdditionalAgreement save(AdditionalAgreement additionalAgreement) {
       return additionalAgreementRepository.save(additionalAgreement);
    }

    public void deleteAll() {
        additionalAgreementRepository.deleteAll();
    }

    public List<AdditionalAgreement> findAll() {
       return additionalAgreementRepository.findAll();
    }

    public Optional<AdditionalAgreement> findById(Long agreementsId) {
        return additionalAgreementRepository.findById(agreementsId);
    }

    public void update(AdditionalAgreement additionalAgreement) {
        additionalAgreementRepository.save(additionalAgreement);
    }

    public void deleteById(Long agreementsId) {
        additionalAgreementRepository.deleteById(agreementsId);
    }
}
