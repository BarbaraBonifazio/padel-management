package it.solvingteam.padelmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.solvingteam.padelmanagement.model.player.Player;
import it.solvingteam.padelmanagement.model.user.Role;
import it.solvingteam.padelmanagement.model.user.User;
import it.solvingteam.padelmanagement.repository.PlayerRepository;

@Service
public class PlayerService {
	
	@Autowired
	PlayerRepository playerRepository;
	@Autowired
	UserService userService;

	public Player insert(Player player) {
		User user = userService.findById(player.getUser().getId());
		user.setRole(Role.PLAYER);
		user = userService.updateUserRole(user);
		player.setUser(user);
		return playerRepository.save(player);
	}
	
	public Player findPlayerWithClubEager(Long playerId) {
		return playerRepository.findPlayerClub(playerId);
	}

	public Player findById(Long playerId) {
		return playerRepository.findById(playerId).get();
	}

	public Player findByUsername(String username) {
		return playerRepository.findPlayerByUser_username(username);
	}
	
	
	
}
