package it.solvingteam.padelmanagement.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.solvingteam.padelmanagement.dto.message.joinProposal.InsertJoinProposalDto;
import it.solvingteam.padelmanagement.model.ProposalStatus;
import it.solvingteam.padelmanagement.model.joinProposal.JoinProposal;
import it.solvingteam.padelmanagement.service.JoinProposalService;

@Component
public class JoinProposalValidator implements Validator{

	@Autowired
	JoinProposalService joinProposalService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return InsertJoinProposalDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		InsertJoinProposalDto insertJoinProposalDto = (InsertJoinProposalDto) target;
		
		List<JoinProposal> proposals = joinProposalService.findProposalByAspiringAssociate(insertJoinProposalDto.getUserDto().getId());
		for(JoinProposal joinProposal : proposals) {
			if(joinProposal.getProposalStatus() == ProposalStatus.PENDING) {
				errors.rejectValue("proposalStatus", "proposalStatusPendingExists", "In attesa di approvazione della proposta effettuata");
			}
			if(joinProposal.getProposalStatus() == ProposalStatus.APPROVED) {
				errors.rejectValue("proposalStatus", "proposalStatusApprovedExists", "Sei già membro di un Circolo!");
			}
		}
	}

}
