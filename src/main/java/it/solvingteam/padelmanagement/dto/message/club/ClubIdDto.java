package it.solvingteam.padelmanagement.dto.message.club;

import javax.validation.constraints.NotNull;

public class ClubIdDto {

	@NotNull
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
