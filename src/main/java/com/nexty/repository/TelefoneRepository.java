package com.nexty.repository;

import com.nexty.domain.Telefone;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Telefone entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {}
