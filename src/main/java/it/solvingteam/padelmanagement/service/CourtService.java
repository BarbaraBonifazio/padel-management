package it.solvingteam.padelmanagement.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.solvingteam.padelmanagement.dto.CourtDto;
import it.solvingteam.padelmanagement.dto.message.court.InsertCourtDto;
import it.solvingteam.padelmanagement.mapper.club.FindClubMapper;
import it.solvingteam.padelmanagement.mapper.court.CourtMapper;
import it.solvingteam.padelmanagement.model.club.Club;
import it.solvingteam.padelmanagement.model.court.Court;
import it.solvingteam.padelmanagement.repository.CourtRepository;

@Service
public class CourtService {

	@Autowired
	private CourtRepository courtRepository;
	@Autowired
	private ClubService clubService;
	@Autowired
	FindClubMapper findClubMapper;
	@Autowired
	CourtMapper courtMapper;
	
	
	public CourtDto insert(@Valid InsertCourtDto insertCourtDto) {
		Club club = clubService.findClubByAdmin(Long.parseLong(insertCourtDto.getAdminId()));
		Court court = courtMapper.convertDtoInsertToEntity(insertCourtDto);
		court.setClub(club);
		court = courtRepository.save(court);
		return courtMapper.convertEntityToDto(court);
	}


	public Court findById(Long id) {
		return this.courtRepository.findById(id).get();
	}
	
	public CourtDto findCourtDtoById(Long id) {
		 Court entity = this.courtRepository.findById(id).get();
		 return courtMapper.convertEntityToDto(entity);
	}
	
	
	
}
