package it.solvingteam.padelmanagement.dto.message;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class GameDto {

	@NotNull
	private String id;
	private Boolean payed;
	private String missingPlayers;
	private String date;
	
	@Valid
	private CourtDto courtDto;
	@Valid
	private PlayerDto playerDto;
	@Valid
	private List<PlayerDto> playersDto;
	@Valid
	private List<String> slotsIds;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Boolean getPayed() {
		return payed;
	}
	public void setPayed(Boolean payed) {
		this.payed = payed;
	}
	public String getMissingPlayers() {
		return missingPlayers;
	}
	public void setMissingPlayers(String missingPlayers) {
		this.missingPlayers = missingPlayers;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public CourtDto getCourtDto() {
		return courtDto;
	}
	public void setCourtDto(CourtDto courtDto) {
		this.courtDto = courtDto;
	}
	public PlayerDto getPlayerDto() {
		return playerDto;
	}
	public void setPlayerDto(PlayerDto playerDto) {
		this.playerDto = playerDto;
	}
	public List<PlayerDto> getPlayersDto() {
		return playersDto;
	}
	public void setPlayersDto(List<PlayerDto> playersDto) {
		this.playersDto = playersDto;
	}
	
	public List<String> getSlotsIds() {
		return slotsIds;
	}
	public void setSlotsIds(List<String> slotsIds) {
		this.slotsIds = slotsIds;
	}
	@Override
	public String toString() {
		return " " + payed + " " + missingPlayers + " " + date + " ";
	}

}
