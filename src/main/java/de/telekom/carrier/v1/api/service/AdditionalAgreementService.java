package de.telekom.carrier.v1.api.service;

import de.telekom.carrier.v1.api.entity.AdditionalAgreement;
import de.telekom.carrier.v1.api.entity.Carrier;
import de.telekom.carrier.v1.api.repository.AdditionalAgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AdditionalAgreementService {

    @Autowired
    AdditionalAgreementRepository additionalAgreementRepository;

    @Transactional
    public AdditionalAgreement save(AdditionalAgreement additionalAgreement) {
       return additionalAgreementRepository.save(additionalAgreement);
    }

    @Transactional
    public void saveController(Carrier carrier) {
        carrier.getAdditionalAgreements().forEach(additionalAgreement -> {
            additionalAgreement.setCarrier(carrier);
            additionalAgreementRepository.save(additionalAgreement);
        });
    }

    public void deleteAll() {
        additionalAgreementRepository.deleteAll();
    }
}
