package it.solvingteam.padelmanagement.dto.message;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class AdminDto {

	@NotNull
	private String id;
	@Valid
	UserDto userDto;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public UserDto getUserDto() {
		return userDto;
	}
	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}
	
}
