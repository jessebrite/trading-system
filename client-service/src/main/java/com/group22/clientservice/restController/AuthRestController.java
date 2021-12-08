package com.group22.clientservice.restController;

import com.group22.clientservice.model.Client;
import com.group22.clientservice.model.ERole;
import com.group22.clientservice.model.Role;
import com.group22.clientservice.payload.request.LoginRequest;
import com.group22.clientservice.payload.request.RegisterRequest;
import com.group22.clientservice.payload.response.JwtResponse;
import com.group22.clientservice.payload.response.MessageResponse;
import com.group22.clientservice.service.ClientService;
import com.group22.clientservice.service.RoleService;
import com.group22.clientservice.service.impl.UserDetailsImplementation;
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

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping
public class AuthRestController {
	private final AuthenticationManager authenticationManager;
	private final ClientService clientService;
	private final RoleService roleService;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtils jwtUtils;

	@PostMapping("login")
	public Object authenticateUser(@Valid @RequestBody LoginRequest loginRequest,
	                               BindingResult result) {
		if (result.hasErrors()) {
			List<String> errors = result.getAllErrors().stream()
				.map(DefaultMessageSourceResolvable::getDefaultMessage)
				.collect(Collectors.toList());
			return new ResponseEntity<>(errors, HttpStatus.OK);
		}

		try {
			Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
					loginRequest.getPassword())
			);

			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtils.generateJwtToken(authentication);

			UserDetailsImplementation userDetails =
				(UserDetailsImplementation) authentication.getPrincipal();

			var role = userDetails.getAuthorities().toString();

			return ResponseEntity.ok(new JwtResponse(
				jwt, userDetails.getId(), userDetails.getFirstName(), userDetails.getLastName(),
				userDetails.getUsername(), userDetails.getEmail(), role));
		} catch (AuthenticationException authException) {
			SecurityContextHolder.getContext().setAuthentication(null);
			return new MessageResponse("Wrong username/password combination");
		}
	}

	@PostMapping("register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest register,
	                                      BindingResult result) {
		if (result.hasErrors()) {
			List<String> errors = result.getAllErrors().stream()
				.map(DefaultMessageSourceResolvable::getDefaultMessage)
				.collect(Collectors.toList());
			return new ResponseEntity<>(errors, HttpStatus.OK);
		}
		if (clientService.existsByUsername(register.getUsername())) {
			return ResponseEntity
				.badRequest()
				.body(new MessageResponse("Username already exists!"));
		}
		if (clientService.existsByEmail(register.getEmail())) {
			return ResponseEntity.badRequest()
				.body(new MessageResponse("Email is already in use!"));
		}

		Client user = new Client(register.getFirstName(), register.getLastName(), register.getUsername(),
			register.getEmail(), passwordEncoder.encode(register.getPassword()), register.getAccount(),
			register.getPortfolio(), register.getCreatedAt());

		String strRole = register.getRole();
//		Set<Role> roles = new HashSet<>();
		Role role;

		if (strRole == null) {
			role = roleService.findByRoleName(ERole.ROLE_USER)
				.orElseThrow(() -> new RuntimeException("Role is not found."));
		} else {
			switch (strRole) {
				case "admin":
					role = roleService.findByRoleName(ERole.ROLE_ADMIN)
						.orElseThrow(() -> new RuntimeException("Role is not found."));
					break;
				case "mod":
					role = roleService.findByRoleName(ERole.ROLE_MODERATOR)
						.orElseThrow(() -> new RuntimeException("Role is not found."));
					break;
				default:
					role = roleService.findByRoleName(ERole.ROLE_USER)
						.orElseThrow(() -> new RuntimeException("Role is not found."));
			}
		}
		user.setRole(role);
		clientService.createNewClient(user);

		return ResponseEntity.ok(new MessageResponse("You have registered successfully!"));
	}
}
