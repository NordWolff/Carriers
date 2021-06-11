package de.telekom.carrier.v1.api.service;

import de.telekom.carrier.v1.api.entity.Carrier;
import de.telekom.carrier.v1.api.entity.ServiceNumber;
import de.telekom.carrier.v1.api.repository.ServiceNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceNumberService {

    @Autowired
    ServiceNumberRepository serviceNumberRepository;

    public List<ServiceNumber> findAll() {
        return serviceNumberRepository.findAll();
    }

    @Transactional
    public ServiceNumber save(ServiceNumber serviceNumber) {
      return serviceNumberRepository.save(serviceNumber);
    }

    @Transactional
    public void saveController(Carrier carrier) {
        carrier.getServiceNumbers().forEach(serviceNumber -> {
            serviceNumber.setCarrier(carrier);
            serviceNumberRepository.save(serviceNumber);
        });
    }

    public void deleteAll() {
        serviceNumberRepository.deleteAll();
    }

    public Optional<ServiceNumber> findById(Long id) {
       return serviceNumberRepository.findById(id);
    }

    public void deleteById(Long serviceNumberId) {
        serviceNumberRepository.deleteById(serviceNumberId);
    }

    public void update(ServiceNumber serviceNumber) {
        serviceNumberRepository.save(serviceNumber);
    }
}
