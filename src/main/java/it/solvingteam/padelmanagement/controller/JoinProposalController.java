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

import it.solvingteam.padelmanagement.dto.JoinProposalDto;
import it.solvingteam.padelmanagement.dto.message.joinProposal.InsertJoinProposalDto;
import it.solvingteam.padelmanagement.exception.BindingResultException;
import it.solvingteam.padelmanagement.model.admin.Admin;
import it.solvingteam.padelmanagement.service.AdminService;
import it.solvingteam.padelmanagement.service.JoinProposalService;
import it.solvingteam.padelmanagement.util.TokenDecripter;
import it.solvingteam.padelmanagement.validator.JoinProposalValidator;

@RestController
@RequestMapping("/api/joinProposal")
public class JoinProposalController {

	@Autowired
	private JoinProposalService joinProposalService;
	@Autowired
	private JoinProposalValidator joinProposalValidator;
	@Autowired
	AdminService adminService;
	
	@PostMapping("/")
	public ResponseEntity<JoinProposalDto> insertJoinProposal(@Valid @RequestBody InsertJoinProposalDto insertJoinProposalDto, 
			BindingResult bindingResult) throws Exception {
		
		joinProposalValidator.validate(insertJoinProposalDto, bindingResult);
		if(bindingResult.hasErrors()) {
			throw new BindingResultException(bindingResult);
		}
		JoinProposalDto joinProposalDto = joinProposalService.insert(insertJoinProposalDto);
		return ResponseEntity.status(HttpStatus.OK).body(joinProposalDto);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<JoinProposalDto>> findAll(){
		String username = TokenDecripter.decripter();
		Admin admin = adminService.findByUsername(username);
		List<JoinProposalDto> joinProposalsDto = joinProposalService.findAllByClub(admin.getId());
		 return ResponseEntity.status(HttpStatus.OK).body(joinProposalsDto);
	}
	
}
