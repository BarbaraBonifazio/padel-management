package it.solvingteam.padelmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.solvingteam.padelmanagement.model.club.Club;

public interface ClubRepository extends JpaRepository<Club, Long>{

}
