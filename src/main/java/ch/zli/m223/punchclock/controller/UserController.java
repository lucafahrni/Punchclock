package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.User;
import ch.zli.m223.punchclock.domain.UserDetailsServiceImpl;
import ch.zli.m223.punchclock.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * @author Luca Fahrni
 * @version 25.11.2020
 * @Class UserController
 */
@RestController
@RequestMapping("/users")
public class UserController {
    private UserDetailsServiceImpl userDetailsService;
    private User applicationUser;
    private final UserRepository applicationUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(UserRepository applicationUserRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.applicationUserRepository = applicationUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole("User");
        applicationUserRepository.save(user);
    }

    @GetMapping("/role")
    @ResponseStatus(HttpStatus.OK)
    public String role(Principal principal) {
        User applicationUser = userDetailsService.getUserByUsername(principal.getName());
        return applicationUser.getRole();
    }
}