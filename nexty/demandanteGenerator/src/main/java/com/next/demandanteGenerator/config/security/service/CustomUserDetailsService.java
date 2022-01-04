package com.next.demandanteGenerator.config.security.service;

import com.next.demandanteGenerator.config.security.model.Usuario;
import com.next.demandanteGenerator.repository.UserRepository;
import com.next.demandanteGenerator.config.security.utility.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = userRepository.findByUsername(username);
        if(usuario ==null) {
            throw new UsernameNotFoundException("User Not Found");
        }
        return new CustomUserDetails(usuario);
    }
}
