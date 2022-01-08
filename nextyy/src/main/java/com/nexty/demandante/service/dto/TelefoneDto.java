package com.nexty.demandante.service.dto;

import lombok.Getter;


@Getter
public class TelefoneDto {

    private Long id;

    private String ddd;

    private String telefone;

    private long demandanteId;

//    public TelefoneDto(Telefone telefone){
//        this.id = telefone.getId();
//        this.ddd = telefone.getDdd();
//        this.telefone = telefone.getTelefone();
//        this.demandanteId = telefone.getDemandante().getId();
//    }
}
