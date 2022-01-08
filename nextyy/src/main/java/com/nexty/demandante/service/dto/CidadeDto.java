package com.nexty.demandante.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeDto {

    private Long id;

    private String cidade;

    private String estado;

    private String uf;

    public CidadeDto() {
        this.id = id;
        this.cidade = cidade;
        this.estado = estado;
        this.uf = uf;
    }
}
