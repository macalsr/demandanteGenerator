package com.next.demandanteGenerator.config.security.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.next.demandanteGenerator.model.security.Permission;
import com.next.demandanteGenerator.model.security.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class UserPrinciple implements UserDetails {

    private Long id;

    private String username;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public static UserPrinciple build(User user) {

        ArrayList<GrantedAuthority> authorities = new ArrayList<>();

        for (Permission permission : user.getPermissions()) {
            authorities.addAll(permission.getUserGroup().getRoles());
        }

        return new UserPrinciple(
                user.getId(),
                user.getName(),
                user.getPassword(),
                authorities
        );

    }

    public List<String> authoritiesToStringList() {
        return this.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
