package it.solvingteam.padelmanagement.dto.message;

import java.util.List;

public class ErrorMessageDto {

	    private List<String> errors;

		public ErrorMessageDto(List<String> errors) {
	        this.errors = errors;
		}
	    
	    public List<String> getErrors() {
	        return errors;
	    }

	    public void setErrors(List<String> errors) {
	        this.errors = errors;
	    }
	    
		



}
