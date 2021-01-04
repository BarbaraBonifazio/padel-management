package it.solvingteam.padelmanagement.dto;

import javax.validation.constraints.NotNull;

public class UserDto {

	@NotNull
	private String id;
	private String name;
	private String surname;
	private String mailAddress;
	private String mobile;
	private String dateOfBirth;
	private String username;
	private String role;
	private Byte[] profilePic;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Byte[] getProfilePic() {
		return profilePic;
	}
	public void setProfilePic(Byte[] profilePic) {
		this.profilePic = profilePic;
	}
	@Override
	public String toString() {
		return " " + username + " " + role + " ";
	}
	
	
}
