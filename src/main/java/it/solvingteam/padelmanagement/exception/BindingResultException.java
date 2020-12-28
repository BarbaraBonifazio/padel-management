package it.solvingteam.padelmanagement.exception;

import org.springframework.validation.BindingResult;

public class BindingResultException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BindingResult bindingResult;
	
	public BindingResultException(BindingResult bindingResult) {
		this.bindingResult = bindingResult;
	}

	public BindingResult getBindingResult() {
		return bindingResult;
	}

	public void setBindingResult(BindingResult bindingResult) {
		this.bindingResult = bindingResult;
	}
	
}
