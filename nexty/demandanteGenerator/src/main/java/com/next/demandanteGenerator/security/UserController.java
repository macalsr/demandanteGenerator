package com.next.demandanteGenerator.security;

import com.next.demandanteGenerator.model.security.User;
import com.next.demandanteGenerator.repository.security.UserRepository;
import com.next.demandanteGenerator.security.dto.UserDto;
import com.next.demandanteGenerator.security.form.user.CreateUserForm;
import com.next.demandanteGenerator.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
@RequestMapping("/v1/security/user")
@AllArgsConstructor
public class UserController extends AbstractController {

    private static final String CONTROLLER_PATH = "/v1/security/user";

    private final UserService userService;
    private final UserRepository userRepository;


    @GetMapping("/{id}/")
    public ResponseEntity<?> getOne(@PathVariable Long id) {

        Optional<User> user = userRepository.findById(id);
        return ResponseEntity
                .ok(new UserDto(new User()));

    }

    @PostMapping("/")
    public ResponseEntity<?> create(@Validated @RequestBody CreateUserForm form) {

        User user = this.userService.create(form);

        return ResponseEntity
                .created(this.buildUriForCreate(user.getId(), CONTROLLER_PATH + "/{id}"))
                .body(user.getId());

    }


    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return null;
    }
}