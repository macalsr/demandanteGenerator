package com.next.demandanteGenerator.repository.security;

import com.next.demandanteGenerator.model.security.UserGroup;
import com.next.demandanteGenerator.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGroupRepository extends AbstractRepository<UserGroup, Integer> {

}