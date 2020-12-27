package it.solvingteam.padelmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.solvingteam.padelmanagement.model.game.Game;

public interface GameRepository extends JpaRepository<Game, Long>{

}
