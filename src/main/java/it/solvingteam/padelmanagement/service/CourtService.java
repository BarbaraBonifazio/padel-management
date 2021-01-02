package it.solvingteam.padelmanagement.service;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.solvingteam.padelmanagement.dto.CourtDto;
import it.solvingteam.padelmanagement.dto.message.SuccessMessageDto;
import it.solvingteam.padelmanagement.dto.message.court.InsertCourtDto;
import it.solvingteam.padelmanagement.mapper.club.FindClubMapper;
import it.solvingteam.padelmanagement.mapper.court.CourtMapper;
import it.solvingteam.padelmanagement.model.club.Club;
import it.solvingteam.padelmanagement.model.court.Court;
import it.solvingteam.padelmanagement.model.game.Game;
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
	
	public CourtDto findCourtDtoById(String id) throws Exception {
		 if(!StringUtils.isNumeric(id)) {
	            throw new Exception("L'id fornito non esiste!");
	        }
		 Court entity = this.courtRepository.findById(Long.parseLong(id)).get();
		 if(entity==null) {
	            throw new Exception("L'id fornito non è valido!");
	        }
		 return courtMapper.convertEntityToDto(entity);
	}

	public List<CourtDto> findAll(String adminId) {
		Club club = clubService.findClubByAdmin(Long.parseLong(adminId));
		List<Court> courts = courtRepository.findAllCourtByClub_Id(club.getId());
		return courtMapper.convertEntityToDto(courts);
	}


	public CourtDto update(CourtDto courtDto) {
		Court courtEntity = courtMapper.convertDtoToEntity(courtDto);
		Court courtDaDb = courtRepository.findById(courtEntity.getId()).get();
		Club club = clubService.findById(courtDaDb.getClub().getId());
		courtEntity.setClub(club);
		courtRepository.save(courtEntity);
		return courtMapper.convertEntityToDto(courtEntity);
	}


	public SuccessMessageDto setStatus(String id) throws Exception {

        if(!StringUtils.isNumeric(id)) {
            throw new Exception("L'id fornito non esiste!");
        }
        Court courtDaDb =courtRepository.findById(Long.parseLong(id)).get();
        if(courtDaDb==null) {
            throw new Exception("L'id fornito non è valido!");
        }

        for(Game game : courtDaDb.getGames()) {
            if(game.getDate().isAfter(LocalDate.now())) {
                throw new Exception("campo con prenotazioni attive!");
            }
            }

        if(!courtDaDb.getIsInactive()) {
        	courtDaDb.setIsInactive(true);
            courtRepository.save(courtDaDb);
        } else {
        	courtDaDb.setIsInactive(false);
            courtRepository.save(courtDaDb);
        }
         SuccessMessageDto successMessage = new SuccessMessageDto("Lo stato del campo ora risulta Inattivo = " + courtDaDb.getIsInactive());
        return successMessage;

    }


	public List<Court> findAllCourtsByClub(Long clubId) {
		return courtRepository.findAllCourtByClub_Id(clubId);
	}

	
}
