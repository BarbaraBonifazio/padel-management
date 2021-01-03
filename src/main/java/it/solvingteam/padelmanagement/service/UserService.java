package it.solvingteam.padelmanagement.service;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.solvingteam.padelmanagement.dto.UserDto;
import it.solvingteam.padelmanagement.dto.message.user.InsertUserMessageDto;
import it.solvingteam.padelmanagement.dto.message.user.UpdateUserDto;
import it.solvingteam.padelmanagement.mapper.user.UserMapper;
import it.solvingteam.padelmanagement.model.user.Role;
import it.solvingteam.padelmanagement.model.user.User;
import it.solvingteam.padelmanagement.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
//    @Autowired
//    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private UserMapper userMapper;

    public Optional<User> findUserByUSername(String username) {
        return userRepository.findByUsername(username);
    }
    
       
    public InsertUserMessageDto signup(InsertUserMessageDto insertUserMessageDto){
    	
        User user = userMapper.convertDtoInsertToEntity(insertUserMessageDto);
//        user.setPassword(passwordEncoder.encode(insertUserMessageDto.getPassword()));
        user.setRole(Role.ROLE_GUEST);
        this.userRepository.save(user);
        return userMapper.convertEntityToDtoInsert(user);
    }


	public UserDto signIn(String username, String password) throws Exception {
		User user = userRepository.findByUsernameAndPassword(username, password);
		UserDto userDto = userMapper.convertEntityToDto(user);
		if(userDto == null) {
			throw new Exception("Errore! Credenziali non valide!");
		}
		return userDto;
	}
	
	public User findById(Long userId) {
		return userRepository.findById(userId).get();
	}
	
	public User updateUserRole(User user) {
		return userRepository.save(user);
	}


	public UserDto findUserDtoById(String userId) throws Exception {
		 if(!StringUtils.isNumeric(userId)) {
	            throw new Exception("L'id fornito non esiste!");
	        }
		return userMapper.convertEntityToDtoForShow(this.findById(Long.parseLong(userId)));
	}


	public UpdateUserDto update(UpdateUserDto updateUserDto) {
		User user = userMapper.convertDtoUpdateToEntity(updateUserDto);
		this.userRepository.save(user);
        return userMapper.convertEntityToDtoUpdate(user);
	}

}
