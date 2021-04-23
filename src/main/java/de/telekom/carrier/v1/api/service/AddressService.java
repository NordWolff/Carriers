package de.telekom.carrier.v1.api.service;

import de.telekom.carrier.v1.api.entity.Address;
import de.telekom.carrier.v1.api.entity.Carrier;
import de.telekom.carrier.v1.api.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Transactional
    public void save(Address address) {
        addressRepository.save(address);
    }
}
