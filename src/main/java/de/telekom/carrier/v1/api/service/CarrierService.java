package de.telekom.carrier.v1.api.service;

import de.telekom.carrier.v1.api.entity.Carrier;
import de.telekom.carrier.v1.api.repository.CarrierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CarrierService {

    @Autowired
    CarrierRepository carrierRepository;

    public List<Carrier> findAll() {
       return carrierRepository.findAll();
    }

    @Transactional
    public Carrier save(Carrier carrier) {
       return carrierRepository.save(carrier);
    }

    public void update(Carrier carrier) {
        carrierRepository.save(carrier);
    }

    public Optional<Carrier> findById(Long carrierId) {
       return carrierRepository.findById(carrierId);
    }

    public void deleteAll() {
        carrierRepository.deleteAll();
    }

    public void deleteById(Long carrierId) {
        carrierRepository.deleteById(carrierId);
    }

    public Optional<Carrier> findByCustomerNumber(String customerNumber) {
       return carrierRepository.findByCostumerNumber(customerNumber);
    }
}
