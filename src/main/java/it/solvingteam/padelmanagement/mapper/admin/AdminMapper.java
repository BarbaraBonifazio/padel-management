package it.solvingteam.padelmanagement.mapper.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.solvingteam.padelmanagement.dto.message.AdminDto;
import it.solvingteam.padelmanagement.mapper.AbstractMapper;
import it.solvingteam.padelmanagement.mapper.club.ClubMapper;
import it.solvingteam.padelmanagement.mapper.user.UserMapper;
import it.solvingteam.padelmanagement.model.admin.Admin;

@Component
public class AdminMapper extends AbstractMapper<Admin, AdminDto> {

	@Autowired
	ClubMapper clubMapper;
	@Autowired
	UserMapper userMapper;

	@Override
	public AdminDto convertEntityToDto(Admin entity) {
		if (entity == null) {
			return null;
		}

		AdminDto dto = new AdminDto();

		dto.setId(String.valueOf(entity.getId()));
		dto.setClubDto(clubMapper.convertEntityToDto(entity.getClub()));
		dto.setUserDto(userMapper.convertEntityToDto(entity.getUser()));

		return dto;

	}

	@Override
	public Admin convertDtoToEntity(AdminDto dto) {
		if (dto == null) {
			return null;
		}

		Admin admin = new Admin();

		admin.setId(Long.parseLong(dto.getId()));
		admin.setClub(clubMapper.convertDtoToEntity(dto.getClubDto()));
		admin.setUser(userMapper.convertDtoToEntity(dto.getUserDto()));

		return admin;

	}

}
