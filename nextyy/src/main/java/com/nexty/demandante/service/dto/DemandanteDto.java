package com.nexty.demandante.service.dto;

import lombok.Getter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;

@Getter
public class DemandanteDto {

    private Long id;

    private String nmDemandante;

    @CPF
    private String cpf;

    @Email
    private String email;

    private TelefoneDto telefoneDto;

    private CidadeDto cidadeDto;

}
