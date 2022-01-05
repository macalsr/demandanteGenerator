package com.next.demandanteGenerator.model.security;


import com.next.demandanteGenerator.model.Auditable;
import com.next.demandanteGenerator.model.Demandante;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Accessors(chain = true)
@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(schema = "security", name = "user")
@NamedEntityGraph(name = "UserWithPermissions", attributeNodes = {@NamedAttributeNode("permissions")})
public class User extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Demandante demandante;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.PERSIST)
    private Set<Permission> permissions = new HashSet<>();

}

