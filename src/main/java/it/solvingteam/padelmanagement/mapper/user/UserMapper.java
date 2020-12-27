package it.solvingteam.padelmanagement.mapper.user;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.solvingteam.padelmanagement.dto.message.UserDto;
import it.solvingteam.padelmanagement.mapper.AbstractMapper;
import it.solvingteam.padelmanagement.mapper.joinProposal.JoinProposalMapper;
import it.solvingteam.padelmanagement.mapper.newClubProposal.NewClubProposalMapper;
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
		dto.setProfilePic(entity.getProfilePic());
		dto.setJoinProposalDto(joinProposalMapper.convertEntityToDto(entity.getJoinProposals()));
		dto.setNewClubProposalDto(newClubProposalMapper.convertEntityToDto(entity.getNewClubProposal()));

		return dto;
	}

	@Override
	public User convertDtoToEntity(UserDto dto) {
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
		user.setJoinProposals(joinProposalMapper.convertDtoToEntity(dto.getJoinProposalDto()));
		user.setNewClubProposal(newClubProposalMapper.convertDtoToEntity(dto.getNewClubProposalDto()));
		
		return user;
	}
	
	

}
