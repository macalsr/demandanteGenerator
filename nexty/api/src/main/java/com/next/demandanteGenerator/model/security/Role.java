package com.next.demandanteGenerator.model.security;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Accessors(chain = true)
@Getter
@Setter
@Entity
@Table(schema = "security", name = "role")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private RoleGroup group;

    @Override
    public String getAuthority() {
        return this.code;
    }

}