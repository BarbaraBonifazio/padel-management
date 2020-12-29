package it.solvingteam.padelmanagement.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.solvingteam.padelmanagement.dto.JoinProposalDto;
import it.solvingteam.padelmanagement.dto.message.joinProposal.InsertJoinProposalDto;
import it.solvingteam.padelmanagement.mapper.joinProposal.JoinProposalMapper;
import it.solvingteam.padelmanagement.model.ProposalStatus;
import it.solvingteam.padelmanagement.model.club.Club;
import it.solvingteam.padelmanagement.model.joinProposal.JoinProposal;
import it.solvingteam.padelmanagement.model.player.Player;
import it.solvingteam.padelmanagement.model.user.User;
import it.solvingteam.padelmanagement.repository.JoinProposalRepository;

@Service
public class JoinProposalService {

	@Autowired
	JoinProposalRepository joinProposalRepository;
	@Autowired
	JoinProposalMapper joinProposalMapper;
	@Autowired
	ClubService clubService;
	@Autowired
	UserService userService;
	@Autowired
	PlayerService playerService;
	@Autowired
	EmailService emailService;
	
	public List<JoinProposal> findProposalByAspiringAssociate(String id) {
		return joinProposalRepository.findProposalByAspiringAssociate_Id(Long.parseLong(id));
	}

	public JoinProposalDto insert(InsertJoinProposalDto insertJoinProposalDto) {
		JoinProposal joinProposal = joinProposalMapper.convertDtoInsertToEntity(insertJoinProposalDto);
		joinProposal.setClub(clubService.findById(Long.parseLong(insertJoinProposalDto.getClubDtoForJoinProposal().getId())));
		joinProposal.setProposalStatus(ProposalStatus.PENDING);
		joinProposal = this.joinProposalRepository.save(joinProposal);
        return joinProposalMapper.convertEntityToDto(joinProposal);
	}

	public List<JoinProposalDto> findAll() {
		List<JoinProposalDto> joinProposalsDto = joinProposalMapper.convertEntityToDto(this.joinProposalRepository.findAll());
		return joinProposalsDto;
	}
	
	public JoinProposal update(JoinProposal joinProposal) {
		return this.joinProposalRepository.save(joinProposal);
	}

	public void joinProposalApproval(String idJoinProposal) throws Exception {
		if(idJoinProposal == null || !StringUtils.isNumeric(idJoinProposal)) {
			throw new Exception("L'id fornito non esiste!");
		}
		
		JoinProposal joinProposal = this.joinProposalRepository.findById(Long.parseLong(idJoinProposal)).get();
		
		if(joinProposal == null) {
			throw new Exception("L'id fornito non è stato trovato!");
		}
		
		if(!(joinProposal.getProposalStatus() == ProposalStatus.PENDING) ){
	        throw new Exception("Proposta già gestita!");
	    }
		
		joinProposal.setProposalStatus(ProposalStatus.APPROVED);
		this.update(joinProposal);
		
		User user = userService.findById(joinProposal.getAspiringAssociate().getId());
		
		Club club = clubService.findById(joinProposal.getClub().getId());
		
		Player player = new Player(joinProposal.getUserLevel(), user, club);
		
		player = playerService.insert(player);
	
		emailService.sendMail(user.getMailAddress(), " Proposta Adesione Circolo Approvata ", 
				" Gentile Utente " + user.getName() + " " + user.getSurname() + ", " 
				+ "\n" + "\n" +
				"la seguente proposta è stata approvata: " + " \n " + joinProposal.toString() + " "
				+ "\n" + "\n" + 
				" Il Suo nuovo ruolo è: " + user.getRole() + " "
				+ "\n" + "\n" +
				"Cordiali saluti, "
				+ "\n" +
				"- Team Padel Management");
		
		
	}

	public void joinProposalRejection(String idJoinProposal) throws Exception {
		if(idJoinProposal == null || !StringUtils.isNumeric(idJoinProposal)) {
			throw new Exception("L'id fornito non esiste!");
		}
		
		JoinProposal joinProposal = this.joinProposalRepository.findById(Long.parseLong(idJoinProposal)).get();
		
		if(joinProposal == null) {
			throw new Exception("L'id fornito non è stato trovato!");
		}
		
		if(!(joinProposal.getProposalStatus() == ProposalStatus.PENDING) ){
	        throw new Exception("Proposta già gestita!");
	    }
		
		joinProposal.setProposalStatus(ProposalStatus.REJECTED);
		this.update(joinProposal);
		
		User user = userService.findById(joinProposal.getAspiringAssociate().getId());
		
		emailService.sendMail(user.getMailAddress(), " Proposta Adesione Circolo Rifiutata ", 
				" Gentile Utente " + user.getName() + " " + user.getSurname() + ", " 
				
				+ "\n" + "\n" +
				"siamo spiacenti di comunicarle che la seguente proposta è stata rifiutata: " + 
				
				" \n " + "\n" + 
				joinProposal.toString() + " "
				+ "\n" + "\n" + 
				
				"Cordiali saluti, "
				+ "\n" +
				"- Team Padel Management");
		
	}

	
	
}
