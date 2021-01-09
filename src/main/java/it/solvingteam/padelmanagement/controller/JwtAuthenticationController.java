package it.solvingteam.padelmanagement.controller;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.solvingteam.padelmanagement.dto.message.user.InsertUserMessageDto;
import it.solvingteam.padelmanagement.exception.BindingResultException;
import it.solvingteam.padelmanagement.model.JwtRequest;
import it.solvingteam.padelmanagement.model.JwtResponse;
import it.solvingteam.padelmanagement.service.JwtUserDetailsService;
import it.solvingteam.padelmanagement.util.JwtTokenUtil;
import it.solvingteam.padelmanagement.validator.UserSignupMessageValidator;

@RestController
@CrossOrigin
@RequestMapping("auth")
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;
	
	@Autowired
	private UserSignupMessageValidator userSignupMessageValidator;
	
	@Autowired
	private JwtUserDetailsService userService;

	@PostMapping("/authenticate")
	public ResponseEntity<?> generateAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
			throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = jwtInMemoryUserDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}

	private void authenticate(String username, String password) throws Exception {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
	@PostMapping("/signUp")
	public ResponseEntity<InsertUserMessageDto> signupUser(@Valid @RequestBody InsertUserMessageDto insertUserMessageDto, 
				BindingResult bindingResult) throws Exception {
		
		userSignupMessageValidator.validate(insertUserMessageDto, bindingResult);
		if(bindingResult.hasErrors()) {
			throw new BindingResultException(bindingResult);
		}
		insertUserMessageDto = userService.signup(insertUserMessageDto);
		return ResponseEntity.status(HttpStatus.OK).body(insertUserMessageDto);
	}
}
