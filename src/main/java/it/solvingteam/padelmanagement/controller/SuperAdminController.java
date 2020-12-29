package it.solvingteam.padelmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.solvingteam.padelmanagement.dto.message.SuccessMessageDto;
import it.solvingteam.padelmanagement.service.NewClubProposalService;

@RestController
@RequestMapping("/api/superAdmin")
public class SuperAdminController {

	@Autowired
	NewClubProposalService newClubProposalService;
	
	@GetMapping("/approve/{idNewClubProposal}")
	public ResponseEntity<SuccessMessageDto> approved(@PathVariable String idNewClubProposal) throws Exception {
		newClubProposalService.clubApproval(idNewClubProposal);
		return ResponseEntity.status(HttpStatus.OK).body(new SuccessMessageDto("Il Club è stato correttamente approvato"));
	}
	
	@GetMapping("/reject/{idNewClubProposal}")
	public ResponseEntity<SuccessMessageDto> rejected(@PathVariable String idNewClubProposal) throws Exception {
		newClubProposalService.clubRejection(idNewClubProposal);
		return ResponseEntity.status(HttpStatus.OK).body(new SuccessMessageDto("Proposta nuovo club rifiutata"));
	}
	
	
}
