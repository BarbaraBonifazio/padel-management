package it.solvingteam.padelmanagement.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.solvingteam.padelmanagement.dto.UserDto;
import it.solvingteam.padelmanagement.dto.message.user.UpdateUserDto;
import it.solvingteam.padelmanagement.exception.BindingResultException;
import it.solvingteam.padelmanagement.service.UserService;
import it.solvingteam.padelmanagement.util.TokenDecripter;
import it.solvingteam.padelmanagement.validator.UserSignupMessageValidator;
import it.solvingteam.padelmanagement.validator.UserUpdateValidator;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private UserUpdateValidator userUpdateValidator;
	
	@GetMapping("/showUserInfo")
	public ResponseEntity<UserDto> show() throws Exception {
		String username = TokenDecripter.decripter();
		UserDto userDto = userService.findUserDtoByUsername(username);
		 return ResponseEntity.status(HttpStatus.OK).body(userDto);
	}
	
	@PutMapping("/updateUserInfo")
	public ResponseEntity<UserDto> update(@Valid @RequestBody UpdateUserDto updateUserDto, 
			BindingResult bindingResult) throws Exception {
		
		userUpdateValidator.validate(updateUserDto, bindingResult);
		if(bindingResult.hasErrors()) {
			throw new BindingResultException(bindingResult);
		}
		UserDto userDto = userService.update(updateUserDto);
		return ResponseEntity.status(HttpStatus.OK).body(userDto);
	}
	
}
