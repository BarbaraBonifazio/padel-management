package it.solvingteam.padelmanagement.dto.message;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class CourtDto {

	@NotNull
	private String id;
	
	private String name;
	private Boolean isInactive;
	private String bookingFee;
	
	@Valid
	private ClubDto clubDto;
	@Valid
	List<GameDto> gamesDto;
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
	public Boolean getIsInactive() {
		return isInactive;
	}
	public void setIsInactive(Boolean isInactive) {
		this.isInactive = isInactive;
	}
	public String getBookingFee() {
		return bookingFee;
	}
	public void setBookingFee(String bookingFee) {
		this.bookingFee = bookingFee;
	}
	public ClubDto getClubDto() {
		return clubDto;
	}
	public void setClubDto(ClubDto clubDto) {
		this.clubDto = clubDto;
	}
	public List<GameDto> getGamesDto() {
		return gamesDto;
	}
	public void setGamesDto(List<GameDto> gamesDto) {
		this.gamesDto = gamesDto;
	}
	
	@Override
	public String toString() {
		return " " + name + " " + isInactive + " " + bookingFee + " ";
	}
	
}
