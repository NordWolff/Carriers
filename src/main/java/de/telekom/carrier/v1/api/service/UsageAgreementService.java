package de.telekom.carrier.v1.api.service;

import de.telekom.carrier.v1.api.entity.Carrier;
import de.telekom.carrier.v1.api.entity.UsageAgreement;
import de.telekom.carrier.v1.api.repository.UsageAgreementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UsageAgreementService {

    @Autowired
    UsageAgreementsRepository usageAgreementsRepository;

    @Transactional
    public void save(UsageAgreement usageAgreement) {
        usageAgreementsRepository.save(usageAgreement);
    }

    @Transactional
    public void saveController(Carrier carrier) {
        carrier.getUsageAgreements().forEach(usageAgreement -> {
            usageAgreement.setCarrier(carrier);
            usageAgreementsRepository.save(usageAgreement);
        });
    }

    public List<UsageAgreement> findAll() {
       return usageAgreementsRepository.findAll();
    }

    public Optional<UsageAgreement> findById(Long agreementsId) {
       return usageAgreementsRepository.findById(agreementsId);
    }

    public void update(UsageAgreement usageAgreement) {
        usageAgreementsRepository.save(usageAgreement);
    }

    public void deleteById(Long agreementsId) {
        usageAgreementsRepository.deleteById(agreementsId);
    }
}
