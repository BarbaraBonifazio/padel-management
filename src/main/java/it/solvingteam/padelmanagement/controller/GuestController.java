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

import it.solvingteam.padelmanagement.dto.ClubDto;
import it.solvingteam.padelmanagement.dto.JoinProposalDto;
import it.solvingteam.padelmanagement.dto.message.joinProposal.InsertJoinProposalDto;
import it.solvingteam.padelmanagement.dto.message.newClubProposal.InsertNewClubProposalDto;
import it.solvingteam.padelmanagement.exception.BindingResultException;
import it.solvingteam.padelmanagement.service.ClubService;
import it.solvingteam.padelmanagement.service.JoinProposalService;
import it.solvingteam.padelmanagement.service.NewClubProposalService;
import it.solvingteam.padelmanagement.validator.JoinProposalValidator;
import it.solvingteam.padelmanagement.validator.NewClubProposalValidator;

@RestController
@RequestMapping("/api/guest")
public class GuestController {

	@Autowired 
	ClubService clubService;
	@Autowired
	private NewClubProposalService newClubProposalService;
	@Autowired
	private NewClubProposalValidator newClubProposalValidator;
	@Autowired
	private JoinProposalService joinProposalService;
	@Autowired
	private JoinProposalValidator joinProposalValidator;
	
	@GetMapping("/findAllClubs")
	public ResponseEntity<List<ClubDto>> findAll(){
		List<ClubDto> newClubDto = clubService.findAll();
		 return ResponseEntity.status(HttpStatus.OK).body(newClubDto);
	}
	
	//ENDPOINT INSERT CLUB PROPOSAL (Proposta Nuovo Circolo) 
	//Il Guest diventerà Admin solo se viene accettata la proposta da parte del SuperAdmin
	
		@PostMapping("/insertNewClubProposal")
		public ResponseEntity<InsertNewClubProposalDto> insertNewClubProposal(@Valid @RequestBody InsertNewClubProposalDto insertNewClubProposalDto, 
				BindingResult bindingResult) throws Exception {
			
			newClubProposalValidator.validate(insertNewClubProposalDto, bindingResult);
			if(bindingResult.hasErrors()) {
				throw new BindingResultException(bindingResult);
			}
			insertNewClubProposalDto = newClubProposalService.insert(insertNewClubProposalDto);
			return ResponseEntity.status(HttpStatus.OK).body(insertNewClubProposalDto);
		}
	
		//ENDPOINT INSERT JOIN PROPOSAL (Proposta di Adesione ad un Circolo Esistente) 
		//Il Guest diventerà Player solo se viene accettata la proposta da parte dell'Admin del Circolo
		
		@PostMapping("/insertClubJoinProposal")
		public ResponseEntity<JoinProposalDto> insertJoinProposal(@Valid @RequestBody InsertJoinProposalDto insertJoinProposalDto, 
				BindingResult bindingResult) throws Exception {
			
			joinProposalValidator.validate(insertJoinProposalDto, bindingResult);
			if(bindingResult.hasErrors()) {
				throw new BindingResultException(bindingResult);
			}
			JoinProposalDto joinProposalDto = joinProposalService.insert(insertJoinProposalDto);
			return ResponseEntity.status(HttpStatus.OK).body(joinProposalDto);
		}
		
		
		
	
}
