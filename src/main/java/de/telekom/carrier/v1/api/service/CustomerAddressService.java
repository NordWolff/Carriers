package de.telekom.carrier.v1.api.service;

import de.telekom.carrier.v1.api.entity.Address;
import de.telekom.carrier.v1.api.entity.CustomerAddress;
import de.telekom.carrier.v1.api.repository.CustomerAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
}
