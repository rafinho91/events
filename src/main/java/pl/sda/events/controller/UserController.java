package pl.sda.events.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.events.model.UserEntity;
import pl.sda.events.service.UserService;

import java.util.Optional;

@Controller
public class UserController {

    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(String email, String password) {
        Optional<UserEntity> byEmail = Optional.ofNullable(userService.findByEmail(email));
        if (byEmail.isPresent() && passwordEncoder.encode(password).equals(byEmail.get().getPassword())) {
            return "create";
        } else {
            return "login";
        }
    }

}
