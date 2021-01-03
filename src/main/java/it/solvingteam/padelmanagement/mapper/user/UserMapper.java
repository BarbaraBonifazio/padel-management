package it.solvingteam.padelmanagement.mapper.user;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.solvingteam.padelmanagement.dto.UserDto;
import it.solvingteam.padelmanagement.dto.message.user.InsertUserMessageDto;
import it.solvingteam.padelmanagement.dto.message.user.UpdateUserDto;
import it.solvingteam.padelmanagement.mapper.AbstractMapper;
import it.solvingteam.padelmanagement.mapper.joinProposal.JoinProposalMapper;
import it.solvingteam.padelmanagement.mapper.newClubProposal.NewClubProposalMapper;
import it.solvingteam.padelmanagement.model.user.Role;
import it.solvingteam.padelmanagement.model.user.User;

@Component
public class UserMapper extends AbstractMapper<User, UserDto>{

	@Autowired
	JoinProposalMapper joinProposalMapper;
	@Autowired
	NewClubProposalMapper newClubProposalMapper;
	
	@Override
	public UserDto convertEntityToDto(User entity) {
		if(entity == null) {
			return null;
		}
		
		UserDto dto = new UserDto();
		
		dto.setId(String.valueOf(entity.getId()));
		dto.setName(entity.getName());
		dto.setSurname(entity.getSurname());
		dto.setMailAddress(entity.getMailAddress());
		dto.setMobile(entity.getMobile());
		dto.setDateOfBirth(String.valueOf(entity.getDateOfBirth()));
		dto.setUsername(entity.getUsername());
		dto.setPassword(entity.getPassword());
		dto.setRole(String.valueOf(entity.getRole()));
		dto.setProfilePic(entity.getProfilePic());

		return dto;
	}
	
	public InsertUserMessageDto convertEntityToDtoInsert(User entity) {
		if(entity == null) {
			return null;
		}
		
		InsertUserMessageDto dto = new InsertUserMessageDto();
		
		dto.setName(entity.getName());
		dto.setSurname(entity.getSurname());
		dto.setMailAddress(entity.getMailAddress());
		dto.setMobile(entity.getMobile());
		dto.setDateOfBirth(String.valueOf(entity.getDateOfBirth()));
		dto.setUsername(entity.getUsername());
		dto.setPassword(entity.getPassword());
		dto.setRole(String.valueOf(entity.getRole()));
		dto.setProfilePic(entity.getProfilePic());

		return dto;
	}

	@Override
	public User convertDtoToEntity(UserDto dto) {
		if(dto == null) {
			return null;
		}
		
		User user = new User();
		
		if(dto.getId() != null) {
			user.setId(Long.parseLong(dto.getId()));
		}
		
		user.setName(dto.getName());
		user.setSurname(dto.getSurname());
		user.setMailAddress(dto.getMailAddress());
		user.setMobile(dto.getMobile());
		user.setDateOfBirth(LocalDate.parse(dto.getDateOfBirth()));
		user.setUsername(dto.getUsername());
		user.setPassword(dto.getPassword());
		user.setRole(Enum.valueOf(Role.class, dto.getRole()));
		user.setProfilePic(dto.getProfilePic());
		
		return user;
	}
	
	public User convertDtoInsertToEntity(InsertUserMessageDto dto) {
		if(dto == null) {
			return null;
		}
		
		User user = new User();
		
		user.setName(dto.getName());
		user.setSurname(dto.getSurname());
		user.setMailAddress(dto.getMailAddress());
		user.setMobile(dto.getMobile());
		user.setDateOfBirth(LocalDate.parse(dto.getDateOfBirth()));
		user.setUsername(dto.getUsername());
		user.setPassword(dto.getPassword());
		user.setProfilePic(dto.getProfilePic());
		
		return user;
	}

	public UserDto convertEntityToDtoForShow(User entity) {
		if(entity == null) {
			return null;
		}
		
		UserDto dto = new UserDto();
		
		dto.setId(String.valueOf(entity.getId()));
		dto.setName(entity.getName());
		dto.setSurname(entity.getSurname());
		dto.setMailAddress(entity.getMailAddress());
		dto.setMobile(entity.getMobile());
		dto.setDateOfBirth(String.valueOf(entity.getDateOfBirth()));
		dto.setUsername(entity.getUsername());
		dto.setRole(String.valueOf(entity.getRole()));
		dto.setProfilePic(entity.getProfilePic());

		return dto;
	}

	public User convertDtoUpdateToEntity(UpdateUserDto dto) {
		if(dto == null) {
			return null;
		}
		
		User user = new User();
		user.setId(Long.parseLong(dto.getId()));
		user.setName(dto.getName());
		user.setSurname(dto.getSurname());
		user.setMailAddress(dto.getMailAddress());
		user.setMobile(dto.getMobile());
		user.setDateOfBirth(LocalDate.parse(dto.getDateOfBirth()));
		user.setUsername(dto.getUsername());
		user.setPassword(dto.getPassword());
		user.setProfilePic(dto.getProfilePic());
		
		return user;
	}

	public UpdateUserDto convertEntityToDtoUpdate(User entity) {
		if(entity == null) {
			return null;
		}
		
		UpdateUserDto dto = new UpdateUserDto();
		dto.setId(String.valueOf(entity.getId()));
		dto.setName(entity.getName());
		dto.setSurname(entity.getSurname());
		dto.setMailAddress(entity.getMailAddress());
		dto.setMobile(entity.getMobile());
		dto.setDateOfBirth(String.valueOf(entity.getDateOfBirth()));
		dto.setUsername(entity.getUsername());
		dto.setPassword(entity.getPassword());
		dto.setProfilePic(entity.getProfilePic());

		return dto;
	}

	
	
	

}
