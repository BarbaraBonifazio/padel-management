package it.solvingteam.padelmanagement.mapper.court;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.solvingteam.padelmanagement.dto.CourtDto;
import it.solvingteam.padelmanagement.mapper.AbstractMapper;
import it.solvingteam.padelmanagement.mapper.club.ClubMapper;
import it.solvingteam.padelmanagement.mapper.game.GameMapper;
import it.solvingteam.padelmanagement.model.court.Court;

@Component
public class CourtMapper extends AbstractMapper<Court, CourtDto>{

	@Autowired
	ClubMapper clubMapper;
	@Autowired
	GameMapper gameMapper;
	
	@Override
	public CourtDto convertEntityToDto(Court entity) {
		if(entity == null) {
			return null;
		}
		
		CourtDto dto = new CourtDto();
		
		dto.setId(String.valueOf(entity.getId()));
		dto.setName(entity.getName());
		dto.setIsInactive(entity.getIsInactive());
		dto.setClubDto(clubMapper.convertEntityToDto(entity.getClub()));
		dto.setGamesDto(gameMapper.convertEntityToDto(entity.getGames()));
		
		return dto;
	}

	@Override
	public Court convertDtoToEntity(CourtDto dto) {
		if(dto == null) {
			return null;
		}
		
		Court entity = new Court();
		
		entity.setId(Long.parseLong(dto.getId()));
		entity.setName(dto.getName());
		entity.setIsInactive(dto.getIsInactive());
		entity.setClub(clubMapper.convertDtoToEntity(dto.getClubDto()));
		entity.setGames(gameMapper.convertDtoToEntity(dto.getGamesDto()));
		
		return entity;
	}
	
	@Override
	public List<CourtDto> convertEntityToDto(List<Court> entities) {
        if (entities == null) {
            return null;
        }

        List<CourtDto> dtos = new ArrayList<>();

        for (Court entity : entities) {
            dtos.add(convertEntityToDto(entity));
        }

        return dtos;
    }

	@Override
    public List<Court> convertDtoToEntity(List<CourtDto> dtos) {
        if (dtos == null) {
            return null;
        }

        List<Court> entities = new ArrayList<>();

        for (CourtDto dto : dtos) {
            entities.add(convertDtoToEntity(dto));
        }

        return entities;
    }
	
}
