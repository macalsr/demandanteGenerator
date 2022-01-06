package com.next.demandanteGenerator.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
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

    @ManyToOne
    private Demandante demandanteId;
}
