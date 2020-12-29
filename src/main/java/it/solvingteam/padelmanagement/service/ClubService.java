package it.solvingteam.padelmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.solvingteam.padelmanagement.dto.ClubDto;
import it.solvingteam.padelmanagement.mapper.club.FindClubMapper;
import it.solvingteam.padelmanagement.model.club.Club;
import it.solvingteam.padelmanagement.repository.ClubRepository;

@Service
public class ClubService {
	
	@Autowired
	ClubRepository clubRepository;
	@Autowired
	FindClubMapper findClubMapper;
	
	public Club insert(Club club) {
		return clubRepository.save(club);
	}

	public List<ClubDto> findAll() {
		List<ClubDto> clubDtoList = findClubMapper.convertEntityToDto(this.clubRepository.findAll());
		return clubDtoList;
	}

}
