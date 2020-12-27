package it.solvingteam.padelmanagement.mapper.newClubProposal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.solvingteam.padelmanagement.dto.message.NewClubProposalDto;
import it.solvingteam.padelmanagement.mapper.AbstractMapper;
import it.solvingteam.padelmanagement.mapper.user.UserMapper;
import it.solvingteam.padelmanagement.model.newClubProposal.NewClubProposal;

@Component
public class NewClubProposalMapper extends AbstractMapper<NewClubProposal, NewClubProposalDto>{

	@Autowired
	UserMapper userMapper;
	
	@Override
	public NewClubProposalDto convertEntityToDto(NewClubProposal entity) {
		if(entity == null) {
			return null;
		}
		
		NewClubProposalDto dto = new NewClubProposalDto();
		
		dto.setId(String.valueOf(entity.getId()));
		dto.setName(entity.getName());
		dto.setCity(entity.getCity());
		dto.setLogo(entity.getLogo());
		dto.setUserDto(userMapper.convertEntityToDto(entity.getUser()));
		
		return dto;
	}

	@Override
	public NewClubProposal convertDtoToEntity(NewClubProposalDto dto) {
		if(dto == null) {
			return null;
		}
		
		NewClubProposal entity = new NewClubProposal();
		
		entity.setId(Long.parseLong(dto.getId()));
		entity.setName(dto.getName());
		entity.setCity(dto.getCity());
		entity.setLogo(dto.getLogo());
		entity.setUser(userMapper.convertDtoToEntity(dto.getUserDto()));
		
		return entity;
	}

}
