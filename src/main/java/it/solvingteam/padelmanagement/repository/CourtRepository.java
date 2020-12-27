package it.solvingteam.padelmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.solvingteam.padelmanagement.model.court.Court;

public interface CourtRepository extends JpaRepository<Court, Long>{

}
