package com.nexty.repository;

import com.nexty.domain.Demandante;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Demandante entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DemandanteRepository extends JpaRepository<Demandante, Long> {}
