package com.next.demandanteGenerator.repository;

import com.next.demandanteGenerator.model.Application;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends AbstractRepository<Application, Integer> {
}
