package com.next.demandanteGenerator.service.dto;

import com.next.demandanteGenerator.model.Sexo;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Id;
import javax.validation.constraints.Email;

public class DemandanteDto {

    private Long id;

    private String nmDemandante;

    @CPF
    private String cpf;

    @Email
    private String email;

    private Sexo sexo;

    private TelefoneDto telefoneDto;

    private CidadeDto cidadeDto;


}
