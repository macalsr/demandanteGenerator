package com.next.demandanteGenerator.security.form.user;

import com.next.demandanteGenerator.model.security.User;
import lombok.Getter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
public class CreateUserForm {

    @NotEmpty
    @Size(max = 255)
    @CPF
    // @Unique(class = User)
    private String identifier;

    @NotEmpty
    @Size(max = 255)
    private String name;

    @NotEmpty
    @Email
    @Size(max = 255)
    // @Unique(class = User, field = "email")
    private String email;

    public User dtoToUser() {

        return (new User())
                .setName(this.getIdentifier())
                .setEmail(this.getEmail());
    }

}
