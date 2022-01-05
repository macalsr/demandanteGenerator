package com.next.demandanteGenerator.service;

import com.next.demandanteGenerator.model.Application;
import com.next.demandanteGenerator.model.security.Permission;
import com.next.demandanteGenerator.model.security.User;
import com.next.demandanteGenerator.model.security.UserGroup;
import com.next.demandanteGenerator.repository.ApplicationRepository;
import com.next.demandanteGenerator.repository.security.UserGroupRepository;
import com.next.demandanteGenerator.repository.security.UserRepository;
import com.next.demandanteGenerator.security.dto.RecoverPasswordTokenDto;
import com.next.demandanteGenerator.security.dto.ResponseDto;
import com.next.demandanteGenerator.security.form.AuthForm;
import com.next.demandanteGenerator.security.form.user.CreateUserForm;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserGroupRepository userGroupRepository;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationRepository applicationRepository;


    private String encodePassword(String password) {
        return this.passwordEncoder.encode(password);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public User create(CreateUserForm form) {

        UserGroup userGroup = this.userGroupRepository.getById(UserGroup.ROOT);
        Application application = this.applicationRepository.getById(Application.NEXTY);

        String password = (new RecoverPasswordTokenDto()).getKeyRecover();
        String pwd = this.encodePassword(password);

        User user = form.dtoToUser();

        user.setPassword(pwd);

        user.getDemandante();

        user.getPermissions().add(
                (new Permission())
                        .setApplication(application)
                        .setUser(user)
                        .setUserGroup(userGroup)
        );
        user = this.userRepository.save(user);
        return user;
    }

    public ResponseDto validateAuthUser(AuthForm form) {
        User user = this.userRepository.findByIdentifier(form.getUsername());

        if (user == null) {
            return new ResponseDto("Erro", "Usuário não cadastrado.", 3000);
        }

        if (!passwordEncoder.matches(form.getPassword(), user.getPassword())) {
            return new ResponseDto("Erro", "Senha incorreta.", 7000);
        }

        return null;
    }
}

