package com.next.demandanteGenerator.model.security;

import com.next.demandanteGenerator.model.Application;
import com.next.demandanteGenerator.model.Auditable;
import com.next.demandanteGenerator.model.Demandante;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Accessors(chain = true)
@Getter
@Setter
@Entity
@Table(schema = "security", name = "permission")
public class Permission extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserGroup userGroup;

    @ManyToOne(fetch = FetchType.LAZY)
    private Demandante demandante;

    @ManyToOne(fetch = FetchType.LAZY)
    private Application application;

}