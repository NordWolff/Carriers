package de.telekom.carrier.v1.api.service;

import de.telekom.carrier.v1.api.entity.Contact;
import de.telekom.carrier.v1.api.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    ContactRepository contactRepository;

    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    @Transactional
    public void save(Contact contact) {
        contactRepository.save(contact);
    }

    public void deleteById(Long contactId) {
        contactRepository.deleteById(contactId);
    }

    public Optional<Contact> findById(Long contactId) {
        return contactRepository.findById(contactId);
    }

    public void update(Contact contact) {
        contactRepository.save(contact);
    }
}
