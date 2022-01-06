package com.next.demandanteGenerator.security.dto;

import com.next.demandanteGenerator.config.security.model.UserPrinciple;
import com.next.demandanteGenerator.model.security.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class UserDto {

    Long id;

    String name;

    String email;

    String password;

    List<String> roles;

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getDemandante().getNmDemandante();
        this.email = user.getEmail();

        this.roles = UserPrinciple.build(user).authoritiesToStringList();
    }

}
