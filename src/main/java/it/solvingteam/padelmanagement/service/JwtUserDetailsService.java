package it.solvingteam.padelmanagement.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.solvingteam.padelmanagement.dto.message.user.InsertUserMessageDto;
import it.solvingteam.padelmanagement.mapper.user.UserMapper;
import it.solvingteam.padelmanagement.model.user.Role;
import it.solvingteam.padelmanagement.model.user.User;
import it.solvingteam.padelmanagement.model.user.UserPrincipal;
import it.solvingteam.padelmanagement.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new UserPrincipal(user);
    }

    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
        
    public InsertUserMessageDto signup(InsertUserMessageDto insertUserMessageDto){
        
        String passwordEncoded = passwordEncoder.encode(insertUserMessageDto.getPassword());
        
        User user = userMapper.convertDtoInsertToEntity(insertUserMessageDto);
        user.setPassword(passwordEncoded);
        user.setRole(Role.GUEST);
        insertUserMessageDto = userMapper.convertEntityToDtoInsert(this.userRepository.save(user));
        return insertUserMessageDto;
    }

}