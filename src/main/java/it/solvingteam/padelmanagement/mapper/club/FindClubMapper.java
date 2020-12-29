package it.solvingteam.padelmanagement.mapper.club;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.solvingteam.padelmanagement.dto.ClubDto;
import it.solvingteam.padelmanagement.mapper.AbstractMapper;
import it.solvingteam.padelmanagement.mapper.admin.AdminMapper;
import it.solvingteam.padelmanagement.model.club.Club;

@Component
public class FindClubMapper extends AbstractMapper<Club, ClubDto> {

	@Autowired
	AdminMapper adminMapper;
	
	@Override
	public ClubDto convertEntityToDto(Club entity) {
		if(entity == null) {
			return null;
		}
		
		ClubDto clubDto = new ClubDto();
		clubDto.setId(String.valueOf(entity.getId()));
		clubDto.setName(entity.getName());
		clubDto.setCity(entity.getCity());
		clubDto.setAddress(entity.getAddress());
		clubDto.setAdminDto(adminMapper.convertEntityToDto(entity.getAdmin()));
		
		return clubDto;
	}

	@Override
	public Club convertDtoToEntity(ClubDto dto) {
		if(dto == null) {
			return null;
		}
		
		Club club = new Club();
		club.setId(Long.parseLong(dto.getId()));
		club.setName(dto.getName());
		club.setCity(dto.getCity());
		club.setAddress(dto.getAddress());
		club.setAdmin(adminMapper.convertDtoToEntity(dto.getAdminDto()));
		
		return club;
	}

}
