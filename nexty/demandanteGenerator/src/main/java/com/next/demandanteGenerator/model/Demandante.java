package com.next.demandanteGenerator.model;

import com.next.demandanteGenerator.model.security.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "demandante", schema = "dev")
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

    @Enumerated
    @Column(name = "sexo")
    private Sexo sexo;

    @ManyToOne
    @JoinColumn(name = "id_telefone")
    private Telefone telefone;

    @JoinColumn(name = "id_user")
    @OneToOne
    private User usuario;

    @OneToOne
    @JoinColumn(name = "id_cidade")
    private Cidade cidade;

//    public Demandante(DemandanteDto, demandantedto) {
//        this.id = id;
//        this.nmDemandante = nmDemandante;
//        this.cdTipoDemandante = cdTipoDemandante;
//        this.cdAtendimento = cdAtendimento;
//        this.email = email;
//        this.cpf = cpf;
//        this.sexo = sexo;
//        this.telefone = telefone;
//        this.user = user;
//        this.cidade = cidade;
//    }
}
