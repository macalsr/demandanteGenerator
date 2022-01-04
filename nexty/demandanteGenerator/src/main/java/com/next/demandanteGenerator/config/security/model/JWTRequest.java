package com.next.demandanteGenerator.config.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JWTRequest {

    private String username;
    private String password;
}
