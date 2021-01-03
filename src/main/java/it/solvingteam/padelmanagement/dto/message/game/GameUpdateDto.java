package it.solvingteam.padelmanagement.dto.message.game;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public class GameUpdateDto {

	@NotBlank
	private String gameId;
	@Valid
	private GameCheckDto gameCheckDto;
	
	public GameUpdateDto() {
	}
	
	public String getGameId() {
		return gameId;
	}
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}
	public GameCheckDto getGameCheckDto() {
		return gameCheckDto;
	}
	public void setGameCheckDto(GameCheckDto gameCheckDto) {
		this.gameCheckDto = gameCheckDto;
	}
	
	
	
}
