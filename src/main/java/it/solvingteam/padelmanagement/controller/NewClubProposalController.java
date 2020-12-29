package it.solvingteam.padelmanagement.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import it.solvingteam.padelmanagement.dto.NewClubProposalDto;
import it.solvingteam.padelmanagement.dto.message.newClubProposal.InsertNewClubProposalDto;
import it.solvingteam.padelmanagement.exception.BindingResultException;
import it.solvingteam.padelmanagement.service.NewClubProposalService;
import it.solvingteam.padelmanagement.validator.NewClubProposalValidator;

@RestController
@RequestMapping("/api/newClubProposal")
public class NewClubProposalController {

	@Autowired
	private NewClubProposalService newClubProposalService;
	@Autowired
	private NewClubProposalValidator newClubProposalValidator;
	
	@PostMapping("/")
	public ResponseEntity<InsertNewClubProposalDto> insertNewClubProposal(@Valid @RequestBody InsertNewClubProposalDto insertNewClubProposalDto, 
			BindingResult bindingResult) throws Exception {
		
		newClubProposalValidator.validate(insertNewClubProposalDto, bindingResult);
		if(bindingResult.hasErrors()) {
			throw new BindingResultException(bindingResult);
		}
		insertNewClubProposalDto = newClubProposalService.insert(insertNewClubProposalDto);
		return ResponseEntity.status(HttpStatus.OK).body(insertNewClubProposalDto);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<NewClubProposalDto>> findAll(){
		List<NewClubProposalDto> newClubProposalsDto = newClubProposalService.findAll();
		 return ResponseEntity.status(HttpStatus.OK).body(newClubProposalsDto);
	}
}
