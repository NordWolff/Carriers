package de.telekom.carrier.v1.api.service;

import de.telekom.carrier.v1.api.entity.OslAgreement;
import de.telekom.carrier.v1.api.repository.OslAgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Service
public class OslAgreementService {

    @Autowired
    OslAgreementRepository oslAgreementRepository;

    public List<OslAgreement> findAll() {
        return oslAgreementRepository.findAll();
    }
}
