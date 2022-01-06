package com.next.demandanteGenerator.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class TokenDto {

    private String token;

    private String type;

}