package com.nexty.demandante.repository;

import com.nexty.demandante.domain.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
    Optional<Cidade> findByDemandanteId(@Param("id") Long id);

}
