package com.next.demandanteGenerator.service.dto;

import com.next.demandanteGenerator.model.Telefone;
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
