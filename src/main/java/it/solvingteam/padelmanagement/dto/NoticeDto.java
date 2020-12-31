package it.solvingteam.padelmanagement.dto;

import javax.validation.constraints.NotBlank;

public class NoticeDto {

	private String id;
	@NotBlank
	private String message;
	private String creationDate;
	private ClubDto clubDto;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public ClubDto getClubDto() {
		return clubDto;
	}
	public void setClubDto(ClubDto clubDto) {
		this.clubDto = clubDto;
	}
	
	@Override
	public String toString() {
		return " " + message + " " + creationDate + " ";
	}
}
