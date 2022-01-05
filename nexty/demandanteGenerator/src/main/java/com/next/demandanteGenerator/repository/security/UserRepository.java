package com.next.demandanteGenerator.repository.security;


import com.next.demandanteGenerator.model.security.User;
import com.next.demandanteGenerator.repository.AbstractRepository;
import com.next.demandanteGenerator.repository.CustomRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends AbstractRepository<User, Long>, CustomRepository<User> {

    @EntityGraph(value = "UserWithPermissions")
    Optional<User> findById(@Param("id") Long id);

    @EntityGraph(value = "UserWithPermissions")
    Optional<User> findByName(@Param("name") String name);

    Optional<User> findByEmail(@Param("email") String email);

    @Query(value ="SELECT * FROM security.user AS u\n" +
            "WHERE u.name = ?1", nativeQuery = true)
    User findByIdentifier(String identifier);


}

