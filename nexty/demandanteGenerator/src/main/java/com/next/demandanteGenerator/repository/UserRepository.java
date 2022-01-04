package com.next.demandanteGenerator.repository;

import com.next.demandanteGenerator.config.security.model.Usuario;
import com.next.demandanteGenerator.config.security.service.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Usuario,Long> {
    Usuario findByUsername(String username);
}
