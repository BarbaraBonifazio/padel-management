package it.solvingteam.padelmanagement.dto.message.newClubProposal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import it.solvingteam.padelmanagement.dto.UserDto;

public class InsertNewClubProposalDto {

	@NotBlank
	private String name;
	@NotBlank
	private String city;
	@NotBlank
	private String address;
	private String proposalStatus;
	private Byte[] logo;
	@Valid
	private UserDto userDto;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getProposalStatus() {
		return proposalStatus;
	}
	public void setProposalStatus(String proposalStatus) {
		this.proposalStatus = proposalStatus;
	}
	public Byte[] getLogo() {
		return logo;
	}
	public void setLogo(Byte[] logo) {
		this.logo = logo;
	}
	public UserDto getUserDto() {
		return userDto;
	}
	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}
	
	
	
}
