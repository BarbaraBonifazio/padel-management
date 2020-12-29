package it.solvingteam.padelmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.solvingteam.padelmanagement.model.club.Club;
import it.solvingteam.padelmanagement.repository.ClubRepository;

@Service
public class ClubService {
	
	@Autowired
	ClubRepository clubRepository;
	
	public Club insert(Club club) {
		return clubRepository.save(club);
	}

}
