package it.solvingteam.padelmanagement.mapper.notice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.solvingteam.padelmanagement.dto.NoticeDto;
import it.solvingteam.padelmanagement.mapper.AbstractMapper;
import it.solvingteam.padelmanagement.mapper.club.ClubMapper;
import it.solvingteam.padelmanagement.model.notice.Notice;

@Component
public class NoticeMapper extends AbstractMapper<Notice, NoticeDto> {

	@Autowired
	ClubMapper clubMapper;

	@Override
	public NoticeDto convertEntityToDto(Notice entity) {
		if (entity == null) {
			return null;
		}

		NoticeDto dto = new NoticeDto();

		dto.setId(String.valueOf(entity.getId()));
		dto.setMessage(entity.getMessage());
		dto.setCreationDate(String.valueOf(entity.getCreationDate()));
		dto.setClubDto(clubMapper.convertEntityToDto(entity.getClub()));

		return dto;

	}

	@Override
	public Notice convertDtoToEntity(NoticeDto dto) {
		if (dto == null) {
			return null;
		}

		Notice entity = new Notice();
		
		if(dto.getId() != null) {
			entity.setId(Long.parseLong(dto.getId()));
		}
		
		entity.setMessage(dto.getMessage());
		entity.setCreationDate(LocalDate.parse(dto.getCreationDate()));
		entity.setClub(clubMapper.convertDtoToEntity(dto.getClubDto()));

		return entity;
	}

	@Override
	public List<NoticeDto> convertEntityToDto(List<Notice> entities) {
		if (entities == null) {
			return null;
		}

		List<NoticeDto> dtos = new ArrayList<>();

		for (Notice entity : entities) {
			dtos.add(convertEntityToDto(entity));
		}

		return dtos;
	}

	@Override
	public List<Notice> convertDtoToEntity(List<NoticeDto> dtos) {
		if (dtos == null) {
			return null;
		}

		List<Notice> entities = new ArrayList<>();

		for (NoticeDto dto : dtos) {
			entities.add(convertDtoToEntity(dto));
		}

		return entities;
	}

}
