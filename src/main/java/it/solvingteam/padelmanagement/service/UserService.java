package it.solvingteam.padelmanagement.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.solvingteam.padelmanagement.dto.UserDto;
import it.solvingteam.padelmanagement.dto.message.user.InsertUserMessageDto;
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
    
       
    public void signup(InsertUserMessageDto insertUserMessageDto){
    	
        User user = userMapper.convertDtoInsertToEntity(insertUserMessageDto);
//        user.setPassword(passwordEncoder.encode(insertUserMessageDto.getPassword()));
        user.setRole(Role.ROLE_GUEST);

        this.userRepository.save(user);
    }


	public UserDto signIn(String username, String password) throws Exception {
		User user = userRepository.findByUsernameAndPassword(username, password);
		UserDto userDto = userMapper.convertEntityToDto(user);
		if(userDto == null) {
			throw new Exception("Errore! Credenziali non valide!");
		}
		return userDto;
	}
    
//  //signup per lo User Rappresentante Nazione
//    public void insertUserNationRepresentative(NationRepresentativeInsertMessageDto nationRepresentativeInsertMessageDto) {
//        String username = nationRepresentativeInsertMessageDto.getUserSignupMessageDto().getUsername();
//        String passwordEncoded = passwordEncoder.encode(nationRepresentativeInsertMessageDto.getUserSignupMessageDto().getPassword());
//        
//        User user = new User();
//        user.setName(nationRepresentativeInsertMessageDto.getName());
//        user.setSurname(nationRepresentativeInsertMessageDto.getSurname());
//        user.setFiscalCode(nationRepresentativeInsertMessageDto.getFiscalCode());
//        user.setUsername(username);
//        user.setPassword(passwordEncoded);
//        user.setRole(Role.RAPPRESENTANTE_NAZIONE);
//
//        this.userRepository.save(user);
//    }
//    
//    public Optional<User> findById(Long id) {
//    	return this.userRepository.findById(id);
//    }
//
//	public UserDto userDtoFromUserEntity(User user) {
//		return userMapper.convertEntityToDto(user);
//	}
//
//	public User update(User user) {
//		user.setRole(Role.RAPPRESENTANTE_NAZIONE);
//		return this.userRepository.save(user);
//	}
//    
//    //signup per lo User Atleta
//    public void insertUserAthlete(AthleteInsertMessageDto athleteInsertMessageDto) {
//        String username = athleteInsertMessageDto.getUserSignupMessageDto().getUsername();
//        String passwordEncoded = passwordEncoder.encode(athleteInsertMessageDto.getUserSignupMessageDto().getPassword());
//        
//        User user = new User();
//        user.setName(athleteInsertMessageDto.getName());
//        user.setSurname(athleteInsertMessageDto.getSurname());
//        user.setFiscalCode(athleteInsertMessageDto.getFiscalCode());
//        user.setUsername(username);
//        user.setPassword(passwordEncoded);
//        user.setRole(Role.ATLETA);
//
//        this.userRepository.save(user);
//    }
}
