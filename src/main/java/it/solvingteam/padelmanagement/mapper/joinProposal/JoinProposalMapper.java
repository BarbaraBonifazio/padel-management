package it.solvingteam.padelmanagement.mapper.joinProposal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.solvingteam.padelmanagement.dto.message.JoinProposalDto;
import it.solvingteam.padelmanagement.mapper.AbstractMapper;
import it.solvingteam.padelmanagement.mapper.club.ClubMapper;
import it.solvingteam.padelmanagement.mapper.user.UserMapper;
import it.solvingteam.padelmanagement.model.joinProposal.JoinProposal;

@Component
public class JoinProposalMapper extends AbstractMapper<JoinProposal, JoinProposalDto> {

	@Autowired
	UserMapper userMapper;
	@Autowired
	ClubMapper clubMapper;

	@Override
	public JoinProposalDto convertEntityToDto(JoinProposal entity) {
		if (entity == null) {
			return null;
		}

		JoinProposalDto dto = new JoinProposalDto();

		dto.setId(String.valueOf(entity.getId()));
		dto.setUserLevel(String.valueOf(entity.getUserLevel()));
		dto.setUserDto(userMapper.convertEntityToDto(entity.getAssociate()));
		dto.setClubDto(clubMapper.convertEntityToDto(entity.getClub()));

		return dto;
	}

	@Override
	public JoinProposal convertDtoToEntity(JoinProposalDto dto) {
		if (dto == null) {
			return null;
		}

		JoinProposal entity = new JoinProposal();

		entity.setId(Long.parseLong(dto.getId()));
		entity.setUserLevel(Integer.parseInt(dto.getUserLevel()));
		entity.setAssociate(userMapper.convertDtoToEntity(dto.getUserDto()));
		entity.setClub(clubMapper.convertDtoToEntity(dto.getClubDto()));

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
