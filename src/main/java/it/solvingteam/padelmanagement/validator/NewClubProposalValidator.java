package it.solvingteam.padelmanagement.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.solvingteam.padelmanagement.dto.message.newClubProposal.InsertNewClubProposalDto;
import it.solvingteam.padelmanagement.model.ProposalStatus;
import it.solvingteam.padelmanagement.model.joinProposal.JoinProposal;
import it.solvingteam.padelmanagement.model.newClubProposal.NewClubProposal;
import it.solvingteam.padelmanagement.service.JoinProposalService;
import it.solvingteam.padelmanagement.service.NewClubProposalService;

@Component
public class NewClubProposalValidator implements Validator {

	@Autowired
	NewClubProposalService newClubProposalService;
	@Autowired
	JoinProposalService joinProposalService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return InsertNewClubProposalDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		InsertNewClubProposalDto insertNewClubProposalDto = (InsertNewClubProposalDto) target;
		
		List<NewClubProposal> newClubProposals = newClubProposalService.findProposalByUser(insertNewClubProposalDto.getUserDto().getId());
		for(NewClubProposal newClubProposal : newClubProposals) {
			if(newClubProposal.getProposalStatus() == ProposalStatus.PENDING) {
				errors.rejectValue("proposalStatus", "proposalStatusPendingExists", "In attesa di approvazione della proposta effettuata");
			}
			if(newClubProposal.getProposalStatus() == ProposalStatus.APPROVED) {
				errors.rejectValue("proposalStatus", "proposalStatusApprovedExists", "Sei già Amministratore di un Circolo!");
			}
		}
		
		List<JoinProposal> joinProposals = joinProposalService.findProposalByAspiringAssociate(insertNewClubProposalDto.getUserDto().getId());
		for(JoinProposal joinProposal : joinProposals) {
			if(joinProposal.getProposalStatus() == ProposalStatus.PENDING) {
				errors.rejectValue("proposalStatus", "proposalStatusPendingExists", 
						"Da questo Account risulta effettuata una richiesta di adesione ad un Circolo esistente " +
								"attualmente in stato: " + joinProposal.getProposalStatus() + "! " +
						 "Pertanto non puoi effettuare una richiesta di Creazione di un Circolo!");
			}
			
			if(joinProposal.getProposalStatus() == ProposalStatus.APPROVED) {
				errors.rejectValue("proposalStatus", "proposalStatusPendingExists", 
							"La tua richiesta di adesione al Circolo " + joinProposal.getClub().getName() + " è stata approvata, "
							+ "	pertanto non puoi più effettuare una richiesta di creazione di un nuovo Circolo con questo Account!");
			}
		}
	}

}
