package it.solvingteam.padelmanagement.dto.message.game;

import javax.validation.constraints.NotBlank;

public class GameUpdateMissingPlayersDto {

	@NotBlank
	private String gameId;
	
	@NotBlank
	private String missingPlayers;

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public String getMissingPlayers() {
		return missingPlayers;
	}

	public void setMissingPlayers(String missingPlayers) {
		this.missingPlayers = missingPlayers;
	}
	
	
	
}
