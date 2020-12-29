package it.solvingteam.padelmanagement.dto.message.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class InsertUserMessageDto {

	@NotBlank
	private String name;
	@NotBlank
	private String surname;
	@NotBlank
	private String username;
	@NotBlank
	private String password;
	@NotBlank
	private String repeatePassword;
	@Email(regexp = ".+@.+\\.[a-z]+")
	private String mailAddress;
	@NotBlank
	private String dateOfBirth;
	@NotBlank
	@Size(message = "inserisci un numero con il prefisso", max = 13, min = 13)
	private String mobile;
	
	private String role;

	private Byte[] profilePic;

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepeatePassword() {
		return repeatePassword;
	}

	public void setRepeatePassword(String repeatePassword) {
		this.repeatePassword = repeatePassword;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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

}
