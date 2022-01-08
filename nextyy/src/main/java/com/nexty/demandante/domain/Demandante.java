package com.nexty.demandante.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "demandante")
public class Demandante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nmDemandante")
    private String nmDemandante;

    @Column(name = "cd_Atendimento")
    private Integer cdAtendimento;

    @Column(name = "email")
    private String email;

    @Column(name = "cpf")
    private String cpf;

    @OneToOne
    @JoinColumn(name = "telefone_id")
    private Telefone telefone;

    @JoinColumn(name = "id_user")
    @OneToOne
    private User user;

    @OneToOne
    @JoinColumn(name = "id_cidade")
    private Cidade cidade;
}
