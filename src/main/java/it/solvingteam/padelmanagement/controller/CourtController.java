package it.solvingteam.padelmanagement.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.solvingteam.padelmanagement.dto.CourtDto;
import it.solvingteam.padelmanagement.dto.message.court.InsertCourtDto;
import it.solvingteam.padelmanagement.exception.BindingResultException;
import it.solvingteam.padelmanagement.service.CourtService;
import it.solvingteam.padelmanagement.validator.CourtValidator;

@RestController
@RequestMapping("/api/court")
public class CourtController {

	@Autowired
	CourtService courtService;
	@Autowired
	CourtValidator courtValidator;
	
	@PostMapping("/")
	public ResponseEntity<CourtDto> insertCourt(@Valid @RequestBody InsertCourtDto insertCourtDto, 
			BindingResult bindingResult) throws Exception {
		
		courtValidator.validate(insertCourtDto, bindingResult);
		if(bindingResult.hasErrors()) {
			throw new BindingResultException(bindingResult);
		}
		CourtDto courtDto = courtService.insert(insertCourtDto);
		return ResponseEntity.status(HttpStatus.OK).body(courtDto);
	}
	
	@GetMapping("/listAll/{adminId}")
	public ResponseEntity<List<CourtDto>> findAll(@PathVariable String adminId){
		List<CourtDto> courtDto = courtService.findAll(adminId);
		 return ResponseEntity.status(HttpStatus.OK).body(courtDto);
	}
	
}
