package it.solvingteam.padelmanagement.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.solvingteam.padelmanagement.model.game.Game;

public interface GameRepository extends JpaRepository<Game, Long>{
	
	
	@Query("From Game g join fetch g.gameCreator c where g.date =?1 and c.club.id =?2")
	List<Game> listAllGamesBooked(LocalDate date, Long clubId);

}
