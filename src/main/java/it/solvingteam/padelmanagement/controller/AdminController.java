package it.solvingteam.padelmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.solvingteam.padelmanagement.dto.message.SuccessMessageDto;
import it.solvingteam.padelmanagement.service.JoinProposalService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	JoinProposalService joinProposalService;
	
	@GetMapping("/approve/{idJoinProposal}")
	public ResponseEntity<SuccessMessageDto> approved(@PathVariable String idJoinProposal) throws Exception {
		joinProposalService.joinProposalApproval(idJoinProposal);
		return ResponseEntity.status(HttpStatus.OK).body(new SuccessMessageDto("La proposta di adesione è stata correttamente approvata"));
	}
	
	@GetMapping("/reject/{idJoinProposal}")
	public ResponseEntity<SuccessMessageDto> rejected(@PathVariable String idJoinProposal) throws Exception {
		joinProposalService.joinProposalRejection(idJoinProposal);
		return ResponseEntity.status(HttpStatus.OK).body(new SuccessMessageDto("La proposta di adesione è stata rifiutata"));
	}
	
}
