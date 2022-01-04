package com.next.demandanteGenerator.model;

import javax.persistence.*;

@Entity
@Table(name = "telefone", schema = "dev")
public class Telefone {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ddd")
    private String ddd;

    @Column(name = "telefone")
    private String telefone;
}
