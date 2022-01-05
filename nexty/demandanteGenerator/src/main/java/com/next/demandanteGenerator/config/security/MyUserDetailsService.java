package com.next.demandanteGenerator.config.security;

import com.next.demandanteGenerator.config.security.model.UserPrinciple;
import com.next.demandanteGenerator.model.security.User;
import com.next.demandanteGenerator.repository.security.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username).orElseThrow(
                () -> new UsernameNotFoundException("User Not Found with -> email: " + username));

        return UserPrinciple.build(user);

    }
}
