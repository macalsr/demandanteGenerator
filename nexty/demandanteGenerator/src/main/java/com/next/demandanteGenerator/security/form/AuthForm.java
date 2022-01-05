package com.next.demandanteGenerator.security.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
public class AuthForm {

    @NotEmpty(message = "{not.empty.validate}")
    private String username;

    @NotEmpty(message = "{not.empty.validate}")
    private String password;

}
