package com.next.demandanteGenerator.repository;

import com.next.demandanteGenerator.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
    Optional<Cidade> findByDemandanteId(@Param("demandanteId") Long demandanteId);

}
