package it.solvingteam.padelmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.solvingteam.padelmanagement.model.player.Player;

public interface PlayerRepository extends JpaRepository<Player, Long>{

}
