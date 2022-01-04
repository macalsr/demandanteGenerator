package com.next.demandanteGenerator.model;

import javax.persistence.*;

@Entity
@Table(name = "cidade", schema = "dev")
public class Cidade {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "uf")
    private String uf;

    @Column(name = "estado")
    private String estado;

}
