package com.next.demandanteGenerator.model.security;

import com.next.demandanteGenerator.model.Auditable;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Accessors(chain = true)
@Getter
@Setter
@Entity
@Table(schema = "security", name = "user_group")
public class UserGroup extends Auditable {

    public static final Integer ROOT = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String description;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Permission> permissions = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            schema = "security",
            name = "user_group_role",
            joinColumns = @JoinColumn(name = "user_group_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> Roles = new HashSet<>();

}
