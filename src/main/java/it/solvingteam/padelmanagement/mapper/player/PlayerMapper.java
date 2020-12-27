package it.solvingteam.padelmanagement.mapper.player;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.solvingteam.padelmanagement.dto.message.PlayerDto;
import it.solvingteam.padelmanagement.mapper.AbstractMapper;
import it.solvingteam.padelmanagement.mapper.club.ClubMapper;
import it.solvingteam.padelmanagement.mapper.game.GameMapper;
import it.solvingteam.padelmanagement.mapper.user.UserMapper;
import it.solvingteam.padelmanagement.model.player.Player;

@Component
public class PlayerMapper extends AbstractMapper<Player, PlayerDto>{

	@Autowired
	UserMapper userMapper;
	@Autowired
	ClubMapper clubMapper;
	@Autowired
	GameMapper gameMapper;
	
	@Override
	public PlayerDto convertEntityToDto(Player entity) {
		if(entity == null) {
			return null;
		}
		
		PlayerDto dto = new PlayerDto();
		
		dto.setId(String.valueOf(entity.getId()));
		dto.setPlayerLevel(String.valueOf(entity.getPlayerLevel()));
		dto.setUserDto(userMapper.convertEntityToDto(entity.getUser()));
		dto.setClubDto(clubMapper.convertEntityToDto(entity.getClub()));
		dto.setGamesDto(gameMapper.convertEntityToDto(entity.getGames()));
		
		return dto;		
	}

	@Override
	public Player convertDtoToEntity(PlayerDto dto) {
		if(dto == null) {
			return null;
		}
		
		Player entity = new Player();
		
		entity.setId(Long.parseLong(dto.getId()));
		entity.setPlayerLevel(Integer.parseInt(dto.getPlayerLevel()));
		entity.setUser(userMapper.convertDtoToEntity(dto.getUserDto()));
		entity.setClub(clubMapper.convertDtoToEntity(dto.getClubDto()));
		entity.setGames(gameMapper.convertDtoToEntity(dto.getGamesDto()));
		
		return entity;
	}
	
	@Override
	public List<PlayerDto> convertEntityToDto(List<Player> entities) {
        if (entities == null) {
            return null;
        }

        List<PlayerDto> dtos = new ArrayList<>();

        for (Player entity : entities) {
            dtos.add(convertEntityToDto(entity));
        }

        return dtos;
    }

	@Override
    public List<Player> convertDtoToEntity(List<PlayerDto> dtos) {
        if (dtos == null) {
            return null;
        }

        List<Player> entities = new ArrayList<>();

        for (PlayerDto dto : dtos) {
            entities.add(convertDtoToEntity(dto));
        }

        return entities;
    }

}
