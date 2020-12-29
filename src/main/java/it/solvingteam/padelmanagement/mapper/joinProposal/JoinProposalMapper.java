package it.solvingteam.padelmanagement.mapper.joinProposal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.solvingteam.padelmanagement.dto.JoinProposalDto;
import it.solvingteam.padelmanagement.dto.message.joinProposal.InsertJoinProposalDto;
import it.solvingteam.padelmanagement.mapper.AbstractMapper;
import it.solvingteam.padelmanagement.mapper.club.ClubMapper;
import it.solvingteam.padelmanagement.mapper.club.FindClubMapper;
import it.solvingteam.padelmanagement.mapper.user.UserMapper;
import it.solvingteam.padelmanagement.model.ProposalStatus;
import it.solvingteam.padelmanagement.model.joinProposal.JoinProposal;

@Component
public class JoinProposalMapper extends AbstractMapper<JoinProposal, JoinProposalDto> {

	@Autowired
	UserMapper userMapper;
	@Autowired
	ClubMapper clubMapper;
	@Autowired
	FindClubMapper findClubMapper;

	@Override
	public JoinProposalDto convertEntityToDto(JoinProposal entity) {
		if (entity == null) {
			return null;
		}

		JoinProposalDto dto = new JoinProposalDto();

		dto.setId(String.valueOf(entity.getId()));
		dto.setUserLevel(String.valueOf(entity.getUserLevel()));
		dto.setProposalStatus(String.valueOf(entity.getProposalStatus()));
		dto.setUserDto(userMapper.convertEntityToDto(entity.getAspiringAssociate()));
		dto.setClubDto(findClubMapper.convertEntityToDto(entity.getClub()));
		
		return dto;
	}
	
	public InsertJoinProposalDto convertEntityToDtoInsert(JoinProposal entity) {
		if (entity == null) {
			return null;
		}

		InsertJoinProposalDto dto = new InsertJoinProposalDto();

		dto.setUserLevel(String.valueOf(entity.getUserLevel()));
		dto.setProposalStatus(String.valueOf(entity.getProposalStatus()));
		dto.setUserDto(userMapper.convertEntityToDto(entity.getAspiringAssociate()));
		
		return dto;
	}

	@Override
	public JoinProposal convertDtoToEntity(JoinProposalDto dto) {
		if (dto == null) {
			return null;
		}

		JoinProposal entity = new JoinProposal();

		if(dto.getId() != null) {
			entity.setId(Long.parseLong(dto.getId()));
		}
		
		entity.setUserLevel(Integer.parseInt(dto.getUserLevel()));
		entity.setProposalStatus(Enum.valueOf(ProposalStatus.class, dto.getProposalStatus()));
		entity.setAspiringAssociate(userMapper.convertDtoToEntity(dto.getUserDto()));
		entity.setClub(clubMapper.convertDtoToEntity(dto.getClubDto()));

		return entity;
	}
	
	public JoinProposal convertDtoInsertToEntity(InsertJoinProposalDto dto) {
		if (dto == null) {
			return null;
		}

		JoinProposal entity = new JoinProposal();
		
		entity.setUserLevel(Integer.parseInt(dto.getUserLevel()));
		entity.setAspiringAssociate(userMapper.convertDtoToEntity(dto.getUserDto()));

		return entity;
	}

	@Override
	public List<JoinProposalDto> convertEntityToDto(List<JoinProposal> entities) {
		if (entities == null) {
			return null;
		}

		List<JoinProposalDto> dtos = new ArrayList<>();

		for (JoinProposal entity : entities) {
			dtos.add(convertEntityToDto(entity));
		}

		return dtos;
	}

	@Override
	public List<JoinProposal> convertDtoToEntity(List<JoinProposalDto> dtos) {
		if (dtos == null) {
			return null;
		}

		List<JoinProposal> entities = new ArrayList<>();

		for (JoinProposalDto dto : dtos) {
			entities.add(convertDtoToEntity(dto));
		}

		return entities;
	}

}
