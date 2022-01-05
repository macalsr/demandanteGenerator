package com.next.demandanteGenerator.security.form.user;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
public class EditPasswordDto {
    @NotEmpty
    @Size(min = 8, max = 10)
    private String currentPassword;

    @NotEmpty
    @Size(min = 8, max = 10)
    private String newPassword;

    @NotEmpty
    @Size(min = 8, max = 10)
    private String confirmNewPassword;
}