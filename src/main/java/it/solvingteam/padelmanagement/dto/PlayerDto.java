package it.solvingteam.padelmanagement.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class PlayerDto {

	@NotNull
	private String id;
	private String playerLevel;
	
	@Valid
	private UserDto userDto;
	@Valid
	private ClubDto clubDto;
	@Valid
	private List<GameDto> gamesDto;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPlayerLevel() {
		return playerLevel;
	}
	public void setPlayerLevel(String playerLevel) {
		this.playerLevel = playerLevel;
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
	public List<GameDto> getGamesDto() {
		return gamesDto;
	}
	public void setGamesDto(List<GameDto> gamesDto) {
		this.gamesDto = gamesDto;
	}
	
	@Override
	public String toString() {
		return " Players level = " + playerLevel + " ";
	}
	
}
