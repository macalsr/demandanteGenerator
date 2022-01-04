package com.next.demandanteGenerator.repository;

import com.next.demandanteGenerator.model.Demandante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DemandanteRepository extends JpaRepository<Demandante, Long> {
        Demandante findByCpf(@Param("cpf") String cpf);
}
