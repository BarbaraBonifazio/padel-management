package it.solvingteam.padelmanagement.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.solvingteam.padelmanagement.dto.message.joinProposal.InsertJoinProposalDto;
import it.solvingteam.padelmanagement.model.ProposalStatus;
import it.solvingteam.padelmanagement.model.joinProposal.JoinProposal;
import it.solvingteam.padelmanagement.model.newClubProposal.NewClubProposal;
import it.solvingteam.padelmanagement.service.JoinProposalService;
import it.solvingteam.padelmanagement.service.NewClubProposalService;

@Component
public class JoinProposalValidator implements Validator{

	@Autowired
	JoinProposalService joinProposalService;
	@Autowired
	NewClubProposalService newClubProposalService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return InsertJoinProposalDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		InsertJoinProposalDto insertJoinProposalDto = (InsertJoinProposalDto) target;
		
		List<JoinProposal> joinProposals = joinProposalService.findProposalByAspiringAssociate(insertJoinProposalDto.getUserDto().getId());
		for(JoinProposal joinProposal : joinProposals) {
			if(joinProposal.getProposalStatus() == ProposalStatus.PENDING) {
				errors.rejectValue("proposalStatus", "proposalStatusPendingExists", "In attesa di approvazione della proposta effettuata");
			}
			if(joinProposal.getProposalStatus() == ProposalStatus.APPROVED) {
				errors.rejectValue("proposalStatus", "proposalStatusApprovedExists", "Sei già membro di un Circolo!");
			}
		}
		
		List<NewClubProposal> newClubProposals = newClubProposalService.findProposalByUser(insertJoinProposalDto.getUserDto().getId());
		for(NewClubProposal newClubProposal : newClubProposals) {
			if(newClubProposal.getProposalStatus() == ProposalStatus.PENDING) {
				errors.rejectValue("proposalStatus", "proposalStatusPendingExists", 
							"Da questo Account risulta effettuata una richiesta di creazione per un Nuovo Circolo " +
									"attualmente in stato: " + newClubProposal.getProposalStatus() + "! " +
							 "Pertanto non puoi effettuare una richiesta di Adesione ad un circolo esistente!");
			}
			
			if(newClubProposal.getProposalStatus() == ProposalStatus.APPROVED) {
				errors.rejectValue("proposalStatus", "proposalStatusPendingExists", 
							"La tua richiesta di creazione del circolo " + newClubProposal.getName() + " è stata approvata "
									+ "pertanto non puoi più effettuare una richiesta di Adesione ad un circolo esistente con questo Account!");
			}
		}
	}

}
