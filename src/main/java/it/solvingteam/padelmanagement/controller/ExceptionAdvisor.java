package it.solvingteam.padelmanagement.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import it.solvingteam.padelmanagement.dto.message.ErrorMessageDto;
import it.solvingteam.padelmanagement.exception.BindingResultException;

@ControllerAdvice(basePackages = "it.solvingteam.padelmanagement.controller")
public class ExceptionAdvisor {


    @ExceptionHandler(BindingResultException.class)
    public ResponseEntity<ErrorMessageDto> handleException(BindingResultException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessageDto(e.getBindingResult().getFieldErrors().stream()
                        .map(obj -> obj.getField() + ": " + obj.getDefaultMessage()).collect(Collectors.toList())));
    }
	
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessageDto> handleException(Exception e){
    	List<String> errors = new ArrayList<String>();
		errors.add(e.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessageDto(errors));
    }
	
}
