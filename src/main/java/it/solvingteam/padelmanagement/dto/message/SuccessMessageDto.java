package it.solvingteam.padelmanagement.dto.message;

public class SuccessMessageDto {

	private String message;

	public SuccessMessageDto(String message) {
        this.message = message;
	}
    
    public String getErrors() {
        return message;
    }

    public void setErrors(String message) {
        this.message = message;
    }
	
}
