package it.solvingteam.padelmanagement.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.solvingteam.padelmanagement.dto.message.user.UpdateUserDto;
import it.solvingteam.padelmanagement.model.user.User;
import it.solvingteam.padelmanagement.service.UserService;

@Component
public class UserUpdateValidator implements Validator {
	
	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> aClass) {
		return UpdateUserDto.class.isAssignableFrom(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		UpdateUserDto updateUserDto = (UpdateUserDto) o;

		if (!updateUserDto.getPassword().equals(updateUserDto.getRepeatePassword())) {
			errors.rejectValue("repeatePassword", "passwordsDoesntMatch", "Password doesn't match");
		}

		Optional<User> userByUsername = userService.findUserByUSername(updateUserDto.getUsername());
		if(userByUsername.isPresent()) {
			if (Long.parseLong(updateUserDto.getId()) != userByUsername.get().getId()){
				errors.rejectValue("username", "usernameAlreadyExists", "Username already exists");
			}
		}
		if (!StringUtils.isBlank(updateUserDto.getDateOfBirth())) {
			try {
				if (new SimpleDateFormat("yyyy-MM-dd").parse(updateUserDto.getDateOfBirth()).after(new Date())) { //controllo su data futura
					errors.rejectValue("dateOfBirth", "dateError", "Invalid date");
				}
			} catch (ParseException e) {
				errors.rejectValue("dateOfBirth", "dateError", "Invalid date");
			}
		}
	}
}

