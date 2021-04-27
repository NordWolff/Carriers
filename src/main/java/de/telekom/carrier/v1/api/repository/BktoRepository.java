package de.telekom.carrier.v1.api.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import de.telekom.carrier.v1.api.entity.Bkto;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BktoRepository extends CrudRepository<Bkto, Long> {

    List<Bkto> findAll();

}
