package it.solvingteam.padelmanagement.mapper.game;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.solvingteam.padelmanagement.dto.GameDto;
import it.solvingteam.padelmanagement.mapper.AbstractMapper;
import it.solvingteam.padelmanagement.mapper.court.CourtMapper;
import it.solvingteam.padelmanagement.mapper.player.PlayerMapper;
import it.solvingteam.padelmanagement.model.game.Game;

@Component
public class GameMapper extends AbstractMapper<Game, GameDto>{

	@Autowired
	CourtMapper courtMapper;
	@Autowired
	PlayerMapper playerMapper;
	
	@Override
	public GameDto convertEntityToDto(Game entity) {
		if(entity == null) {
			return null;
		}	
		
		GameDto dto = new GameDto();
		
		dto.setId(String.valueOf(entity.getId()));
		dto.setPaid(entity.getPaid());
		dto.setMissingPlayers(String.valueOf(entity.getMissingPlayers()));
		dto.setDate(String.valueOf(entity.getDate()));
		dto.setCourtDto(courtMapper.convertEntityToDto(entity.getCourt()));
		dto.setPlayerDto(playerMapper.convertEntityToDto(entity.getGameCreator()));
		dto.setPlayersDto(playerMapper.convertEntityToDto(entity.getOtherPlayers()));
		
		return dto;

		//private List<Slot> slots = new ArrayList<>();
		//questo andrà passato tramite SERVICE PERCHE' LO SLOT ANDRA' RICAVATO DIRETTAMENTE DA DB TRAMITE ID
	}

	@Override
	public Game convertDtoToEntity(GameDto dto) {
		if(dto == null) {
			return null;
		}
		
		Game entity = new Game();
		
		if(dto.getId() != null) {
			entity.setId(Long.parseLong(dto.getId()));
		}
		
		entity.setPaid(dto.getPaid());
		entity.setMissingPlayers(Integer.parseInt(dto.getMissingPlayers()));
		entity.setDate(LocalDate.parse(dto.getDate()));
		entity.setCourt(courtMapper.convertDtoToEntity(dto.getCourtDto()));
		entity.setGameCreator(playerMapper.convertDtoToEntity(dto.getPlayerDto()));
		entity.setOtherPlayers(playerMapper.convertDtoToEntity(dto.getPlayersDto()));
		
		return entity;		
		
		//private List<Slot> slots = new ArrayList<>();
		//questo andrà passato tramite SERVICE PERCHE' LO SLOT ANDRA' RICAVATO DIRETTAMENTE DA DB TRAMITE ID
	}
	
	public List<GameDto> convertEntityToDto(List<Game> entities) {
        if (entities == null) {
            return null;
        }

        List<GameDto> dtos = new ArrayList<>();

        for (Game entity : entities) {
            dtos.add(convertEntityToDto(entity));
        }

        return dtos;
    }

    public List<Game> convertDtoToEntity(List<GameDto> dtos) {
        if (dtos == null) {
            return null;
        }

        List<Game> entities = new ArrayList<>();

        for (GameDto dto : dtos) {
            entities.add(convertDtoToEntity(dto));
        }

        return entities;
    }

}
