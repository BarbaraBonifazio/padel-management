package it.solvingteam.padelmanagement.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.solvingteam.padelmanagement.dto.NewClubProposalDto;
import it.solvingteam.padelmanagement.dto.message.newClubProposal.InsertNewClubProposalDto;
import it.solvingteam.padelmanagement.mapper.newClubProposal.NewClubProposalMapper;
import it.solvingteam.padelmanagement.model.ProposalStatus;
import it.solvingteam.padelmanagement.model.admin.Admin;
import it.solvingteam.padelmanagement.model.club.Club;
import it.solvingteam.padelmanagement.model.newClubProposal.NewClubProposal;
import it.solvingteam.padelmanagement.model.user.User;
import it.solvingteam.padelmanagement.repository.NewClubProposalRepository;

@Service
public class NewClubProposalService {
	
	@Autowired
	NewClubProposalRepository newClubProposalRepository;
	@Autowired
	NewClubProposalMapper newClubProposalMapper;
	@Autowired
	UserService userService;
	@Autowired
	AdminService adminService;
	@Autowired
	ClubService clubService;

	public InsertNewClubProposalDto insert(InsertNewClubProposalDto insertNewClubProposalDto){
    	
		NewClubProposal newClubProposal = newClubProposalMapper.convertDtoInsertToEntity(insertNewClubProposalDto);
		newClubProposal.setProposalStatus(ProposalStatus.PENDING);
        newClubProposal = this.newClubProposalRepository.save(newClubProposal);
        return newClubProposalMapper.convertEntityToDtoInsert(newClubProposal);
    }
	
	public List<NewClubProposal> findProposalByUser(String idUser) {
		Long id = Long.parseLong(idUser);
		return this.newClubProposalRepository.findAllNewClubProposalByClubCreator_Id(id);
	}

	public List<NewClubProposalDto> findAll() {
		List<NewClubProposalDto> newClubProposalDto = newClubProposalMapper.convertEntityToDto(this.newClubProposalRepository.findAll());
		return newClubProposalDto;
	}
	
	public NewClubProposal update(NewClubProposal newClubProposal) {
		return this.newClubProposalRepository.save(newClubProposal);
	}
	
	public void clubApproval(String idNewClubProposal) throws Exception {
		if(idNewClubProposal == null || !StringUtils.isNumeric(idNewClubProposal)) {
			throw new Exception("L'id fornito non esiste!");
		}
		
		NewClubProposal newClubProposal = this.newClubProposalRepository.findById(Long.parseLong(idNewClubProposal)).get();
		
		if(newClubProposal == null) {
			throw new Exception("L'id fornito non è stato trovato!");
		}
		
		newClubProposal.setProposalStatus(ProposalStatus.APPROVED);
		this.update(newClubProposal);
		
		User user = userService.findById(newClubProposal.getClubCreator().getId());
		
		Admin admin = adminService.insert(user);
		
		Club newClub = new Club(newClubProposal.getName(), newClubProposal.getCity(), newClubProposal.getAddress(), admin);
		
		newClub = clubService.insert(newClub);
		

		//manda mail di conferma all'indirizzo mail dello user che è diventato Admin
		
	}
	
	public void clubRejection(String idNewClubProposal) throws Exception {
		if(idNewClubProposal == null || !StringUtils.isNumeric(idNewClubProposal)) {
			throw new Exception("L'id fornito non esiste!");
		}
		
		NewClubProposal newClubProposal = this.newClubProposalRepository.findById(Long.parseLong(idNewClubProposal)).get();
		
		if(newClubProposal == null) {
			throw new Exception("L'id fornito non è stato trovato!");
		}
		
		newClubProposal.setProposalStatus(ProposalStatus.REJECTED);
		this.update(newClubProposal);		

		//manda mail di conferma all'indirizzo mail dello user che è rimasto Guest
		
	}
	
}
