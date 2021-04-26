package de.telekom.carrier.v1.api.service;

import de.telekom.carrier.v1.api.entity.Address;
import de.telekom.carrier.v1.api.entity.CustomerAddress;
import de.telekom.carrier.v1.api.repository.CustomerAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerAddressService {

    @Autowired
    CustomerAddressRepository customerAddressRepository;

    @Transactional
    public void save(CustomerAddress address) {
        customerAddressRepository.save(address);
    }

    public List<CustomerAddress> findAll() {
        return (List<CustomerAddress>) customerAddressRepository.findAll();
    }

    public void deleteById(Long customerId) {
        customerAddressRepository.deleteById(customerId);
    }

    public Optional<CustomerAddress> findById(Long carrierId) {
        return customerAddressRepository.findById(carrierId);
    }

    public void update(CustomerAddress customerAddress) {
        customerAddressRepository.save(customerAddress);
    }
}
