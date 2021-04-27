package de.telekom.carrier.v1.api.service;

import de.telekom.carrier.v1.api.entity.Bkto;
import de.telekom.carrier.v1.api.repository.BktoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BktoService {

    @Autowired
    private BktoRepository bktoRepository;

    public List<Bkto> findAll() {
        return bktoRepository.findAll();
    }

    @Transactional
    public void save(Bkto bkto) {
        bktoRepository.save(bkto);
    }

    public Optional<Bkto> findById(Long accountsId) {
      return bktoRepository.findById(accountsId);
    }

    public void update(Bkto bkto) {
        bktoRepository.save(bkto);
    }

    public void deleteById(Long accountsId) {
        bktoRepository.deleteById(accountsId);
    }

    public void delete(Bkto respBkto) {
        bktoRepository.delete(respBkto);
    }

    public void deleteAll() {
        bktoRepository.deleteAll();
    }
}
