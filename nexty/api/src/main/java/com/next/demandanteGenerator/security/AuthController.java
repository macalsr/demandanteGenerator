package com.next.demandanteGenerator.security;


import com.next.demandanteGenerator.config.security.jwt.JwtProvider;
import com.next.demandanteGenerator.security.dto.ResponseDto;
import com.next.demandanteGenerator.security.dto.TokenDto;
import com.next.demandanteGenerator.security.form.AuthForm;
import com.next.demandanteGenerator.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/v1/auth")
@AllArgsConstructor
public class AuthController extends AbstractController {

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final JwtProvider tokenProvider;

    @PostMapping("/login/")
    public ResponseEntity<?> auth(@Validated @RequestBody AuthForm form) {
        ResponseDto validateAuth = this.userService.validateAuthUser(form);

        if (validateAuth != null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(validateAuth);
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        form.getUsername(),
                        form.getPassword()
                )
        );

        return  ResponseEntity.ok(new TokenDto(this.tokenProvider.createToken(authentication), "Bearer"));
    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return null;
    }
}