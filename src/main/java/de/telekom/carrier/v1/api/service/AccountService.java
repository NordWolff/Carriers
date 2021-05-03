package de.telekom.carrier.v1.api.service;

import de.telekom.carrier.v1.api.entity.Account;
import de.telekom.carrier.v1.api.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Transactional
    public void save(Account account) {
        accountRepository.save(account);
    }

    public Optional<Account> findById(Long accountsId) {
      return accountRepository.findById(accountsId);
    }

    public void update(Account account) {
        accountRepository.save(account);
    }

    public void deleteById(Long accountsId) {
        accountRepository.deleteById(accountsId);
    }

    public void delete(Account account) {
        accountRepository.delete(account);
    }

    public void deleteAll() {
        accountRepository.deleteAll();
    }
}
