package it.solvingteam.padelmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.solvingteam.padelmanagement.model.player.Player;

public interface PlayerRepository extends JpaRepository<Player, Long>{
	
	
	@Query("Select p From Player p JOIN FETCH p.club c where p.id = ?1")
	Player findPlayerClub(Long playerId);
}
