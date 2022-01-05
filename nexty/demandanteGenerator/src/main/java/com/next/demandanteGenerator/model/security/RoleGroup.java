package com.next.demandanteGenerator.model.security;

import com.next.demandanteGenerator.model.Auditable;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Set;

@Accessors(chain = true)
@Getter
@Setter
@Entity
@Table(schema = "security", name = "role_group")
public class RoleGroup extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Role> roles;
}
