package it.solvingteam.padelmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.solvingteam.padelmanagement.dto.JoinProposalDto;
import it.solvingteam.padelmanagement.dto.message.joinProposal.InsertJoinProposalDto;
import it.solvingteam.padelmanagement.mapper.joinProposal.JoinProposalMapper;
import it.solvingteam.padelmanagement.model.ProposalStatus;
import it.solvingteam.padelmanagement.model.joinProposal.JoinProposal;
import it.solvingteam.padelmanagement.repository.JoinProposalRepository;

@Service
public class JoinProposalService {

	@Autowired
	JoinProposalRepository joinProposalRepository;
	@Autowired
	JoinProposalMapper joinProposalMapper;
	@Autowired
	ClubService clubService;
	
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

	
	
}
