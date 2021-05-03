package de.telekom.carrier.v1.api.repository;

import de.telekom.carrier.v1.api.entity.Account;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    List<Account> findAll();

}
