package it.solvingteam.padelmanagement.dto.message.game;

import java.util.List;

import javax.validation.constraints.NotNull;

import it.solvingteam.padelmanagement.dto.CourtDto;

public class GameCheckDto {

	private String date;
	@NotNull
	private List<String> slotsIds;
	@NotNull
	private String playerId;
	private CourtDto courtDto;
	private String missingPlayers;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public List<String> getSlotsIds() {
		return slotsIds;
	}
	public void setSlotsIds(List<String> slotsIds) {
		this.slotsIds = slotsIds;
	}
	public String getPlayerId() {
		return playerId;
	}
	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}
	public CourtDto getCourtDto() {
		return courtDto;
	}
	public void setCourtDto(CourtDto courtDto) {
		this.courtDto = courtDto;
	}
	public String getMissingPlayers() {
		return missingPlayers;
	}
	public void setMissingPlayers(String missingPlayers) {
		this.missingPlayers = missingPlayers;
	}
	
}
