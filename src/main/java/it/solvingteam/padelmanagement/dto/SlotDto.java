package it.solvingteam.padelmanagement.dto;

import javax.validation.constraints.NotNull;

public class SlotDto {
	
	@NotNull
	private String id;
	private String slotName;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSlotName() {
		return slotName;
	}
	public void setSlotName(String slotName) {
		this.slotName = slotName;
	}
	
	@Override
	public String toString() {
		return " " + slotName + " ";
	}
	
}
