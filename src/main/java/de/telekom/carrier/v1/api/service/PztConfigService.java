package de.telekom.carrier.v1.api.service;

import de.telekom.carrier.v1.api.entity.Carrier;
import de.telekom.carrier.v1.api.entity.PztConfig;
import de.telekom.carrier.v1.api.repository.PztConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PztConfigService {

    @Autowired
    PztConfigRepository pztConfigRepository;

    @Transactional
    public void save(PztConfig pztConfig) {
        pztConfigRepository.save(pztConfig);
    }

    @Transactional
    public void saveController(Carrier carrier) {
        //carrier.getPztConfig().setCarrier(carrier);
        pztConfigRepository.save(carrier.getPztConfig());
    }
}
