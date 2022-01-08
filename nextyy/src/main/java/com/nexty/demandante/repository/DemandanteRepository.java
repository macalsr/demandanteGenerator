package com.nexty.demandante.repository;

import com.nexty.demandante.domain.Demandante;
import com.nexty.demandante.service.dto.DemandanteDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DemandanteRepository extends JpaRepository<Demandante, Long> {
        Optional<Demandante> findByCpf(@Param("cpf") String cpf);

    Demandante findAll(Demandante demandante);
}
