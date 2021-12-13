package com.group22.clientservice.restController;

import com.group22.clientservice.model.Client;
import com.group22.clientservice.model.enums.Role;
import com.group22.clientservice.payload.request.LoginRequest;
import com.group22.clientservice.payload.request.RegisterRequest;
import com.group22.clientservice.service.ClientService;
import com.group22.clientservice.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthRestController {
    private final AuthenticationManager authenticationManager;
    private final ClientService clientService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest,
                                              BindingResult result) {
//        if (result.hasErrors()) {
//            List<String> errors = result.getAllErrors().stream()
//                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
//                    .collect(Collectors.toList());
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.toString());
//        }

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                            loginRequest.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            Client client = clientService.findClientByUsername(loginRequest.getUsername()).get();
            String jwt = jwtUtils.generateJwtToken(client);

            return new ResponseEntity<>(jwt, HttpStatus.OK);
        } catch (AuthenticationException e) {
            SecurityContextHolder.getContext().setAuthentication(null);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong username/password combination");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong username/password combination");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest register,
                                          BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(errors, HttpStatus.OK);
        }

        if (clientService.existsByUsername(register.getUsername())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Username already exists!");
        }

        if (clientService.existsByEmail(register.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email is already in use!");
        }

        Client user = new Client(register.getFirstName(), register.getLastName(), register.getUsername(),
                register.getEmail(), passwordEncoder.encode(register.getPassword()), register.getAccount(),
                register.getPortfolio());

        var strRole = register.getRole();
        Role role;

        if (strRole == null)
            user.setRole(Role.ROLE_USER);

//        switch (strRole) {
//            case ROLE_ADMIN:
//                role = Role.ROLE_ADMIN;
//                break;
//            case ROLE_MODERATOR:
//                role = Role.ROLE_MODERATOR;
//                break;
//            default:
//                role = Role.ROLE_USER;
//        }
        clientService.createNewClient(user);
        return new ResponseEntity<>("You have registered successfully!", HttpStatus.OK);
    }
}
