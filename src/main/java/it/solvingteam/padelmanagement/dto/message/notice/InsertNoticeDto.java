package it.solvingteam.padelmanagement.dto.message.notice;

import javax.validation.constraints.NotBlank;

public class InsertNoticeDto {

	@NotBlank
	private String message;
	@NotBlank
	private String adminId;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	
	
}
