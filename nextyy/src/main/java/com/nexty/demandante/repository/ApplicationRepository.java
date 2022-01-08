package com.nexty.demandante.repository;

import com.nexty.demandante.management.Application;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends AbstractRepository<Application, Integer> {
}
