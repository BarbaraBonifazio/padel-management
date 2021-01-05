package it.solvingteam.padelmanagement.dto.message.game;

import javax.validation.constraints.NotBlank;

public class GameJoinDto {

	@NotBlank
	private String playerId;
	@NotBlank
	private String gameId;
	
	public String getPlayerId() {
		return playerId;
	}
	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}
	public String getGameId() {
		return gameId;
	}
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}
}
