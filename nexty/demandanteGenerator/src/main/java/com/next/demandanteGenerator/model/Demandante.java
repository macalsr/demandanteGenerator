package com.next.demandanteGenerator.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "demandante", schema = "dev")
public class Demandante {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "nmDemandante")
    private String nmDemandante;

    @Column(name = "cdAtendimento")
    private Integer cdTipoDemandante;

    @Column(name = "cdAtendimento")
    private String cdAtendimento;

    @Column(name = "email")
    private String email;

    @Column(name = "cpf")
    private String cpf;

    @Enumerated(EnumType.STRING)
    @Column(name = "sexo")
    private String sexo;

    @ManyToMany
    @JoinColumn(name = "id_telefone")
    private List<Telefone> telefone;

    @JoinColumn(name = "id_user")
    @OneToOne
    private User user;

    @ManyToMany
    @JoinColumn(name = "id_cidade")
    private List<Cidade> cidade;

}
