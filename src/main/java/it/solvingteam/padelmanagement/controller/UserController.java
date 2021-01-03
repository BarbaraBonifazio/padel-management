package it.solvingteam.padelmanagement.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.solvingteam.padelmanagement.dto.UserDto;
import it.solvingteam.padelmanagement.dto.message.user.InsertUserMessageDto;
import it.solvingteam.padelmanagement.dto.message.user.LoginUserDto;
import it.solvingteam.padelmanagement.dto.message.user.UpdateUserDto;
import it.solvingteam.padelmanagement.exception.BindingResultException;
import it.solvingteam.padelmanagement.service.UserService;
import it.solvingteam.padelmanagement.validator.UserSignupMessageValidator;
import it.solvingteam.padelmanagement.validator.UserUpdateValidator;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	UserSignupMessageValidator userSignupMessageValidator;
	@Autowired
	UserUpdateValidator userUpdateValidator;
	
	@PostMapping("/")
	public ResponseEntity<InsertUserMessageDto> signupUser(@Valid @RequestBody InsertUserMessageDto insertUserMessageDto, 
				BindingResult bindingResult) throws Exception {
		
		userSignupMessageValidator.validate(insertUserMessageDto, bindingResult);
		if(bindingResult.hasErrors()) {
			throw new BindingResultException(bindingResult);
		}
		insertUserMessageDto = userService.signup(insertUserMessageDto);
		return ResponseEntity.status(HttpStatus.OK).body(insertUserMessageDto);
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserDto> signupUser(@Valid @RequestBody LoginUserDto loginUserDto) throws Exception {
	
		UserDto userDto = userService.signIn(loginUserDto.getUsername(), loginUserDto.getPassword());
	
	return ResponseEntity.status(HttpStatus.OK).body(userDto);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> show(@PathVariable String userId) throws Exception {
		UserDto userDto = userService.findUserDtoById(userId);
		 return ResponseEntity.status(HttpStatus.OK).body(userDto);
	}
	
	@PutMapping("/")
	public ResponseEntity<UpdateUserDto> update(@Valid @RequestBody UpdateUserDto updateUserDto, 
			BindingResult bindingResult) throws Exception {
		
		userUpdateValidator.validate(updateUserDto, bindingResult);
		if(bindingResult.hasErrors()) {
			throw new BindingResultException(bindingResult);
		}
		updateUserDto = userService.update(updateUserDto);
		return ResponseEntity.status(HttpStatus.OK).body(updateUserDto);
	}
	
}
