package it.solvingteam.padelmanagement.dto.message;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class JoinProposalDto {

	@NotNull
	private String id;
	private String userLevel;
	private String proposalStatus;
	@Valid
	private UserDto userDto;
	@Valid
	private ClubDto clubDto;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	public ClubDto getClubDto() {
		return clubDto;
	}
	public void setClubDto(ClubDto clubDto) {
		this.clubDto = clubDto;
	}
	
	@Override
	public String toString() {
		return " " + userLevel + " " + proposalStatus + " ";
	}
	
}
