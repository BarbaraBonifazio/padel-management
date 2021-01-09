package it.solvingteam.padelmanagement.dto.message.joinProposal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import it.solvingteam.padelmanagement.dto.UserDto;
import it.solvingteam.padelmanagement.dto.message.club.ClubIdDto;

public class InsertJoinProposalDto {

	@NotBlank
	private String userLevel;
	private String proposalStatus;
	@Valid
	private UserDto userDto;
	@Valid
	private ClubIdDto clubIdDto;
	
	public String getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}
	public String getProposalStatus() {
		return proposalStatus;
	}
	public void setProposalStatus(String proposalStatus) {
		this.proposalStatus = proposalStatus;
	}
	public UserDto getUserDto() {
		return userDto;
	}
	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}
	public ClubIdDto getClubIdDto() {
		return clubIdDto;
	}
	public void setClubIdDto(ClubIdDto clubIdDto) {
		this.clubIdDto = clubIdDto;
	}

	@Override
	public String toString() {
		return "Nome: " + userDto.getName() +
				"\n" +
				"Cognome: " + userDto.getSurname() + 
				"\n" +
				"Username: " + userDto.getSurname() + 
				"\n" +
				"Data di nascita: " + userDto.getDateOfBirth() + 
				"\n" + 
				"Telefono: " + userDto.getMobile() +
				"\n" + 
				"Ruolo: " + userDto.getRole();
	}
	
}
