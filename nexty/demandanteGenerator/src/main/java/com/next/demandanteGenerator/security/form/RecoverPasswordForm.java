package com.next.demandanteGenerator.security.form;


import com.next.demandanteGenerator.validation.Password;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
public class RecoverPasswordForm {
    @NotEmpty
    private String token;

    @NotEmpty
    @Password
    @Size(min = 8, max = 10)
    private String password;

    @NotEmpty
    @Password
    @Size(min = 8, max = 10)
    private String confirmPassword;
}
