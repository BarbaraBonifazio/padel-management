package it.solvingteam.padelmanagement.validator;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.solvingteam.padelmanagement.dto.CourtDto;
import it.solvingteam.padelmanagement.model.club.Club;
import it.solvingteam.padelmanagement.model.court.Court;
import it.solvingteam.padelmanagement.model.game.Game;
import it.solvingteam.padelmanagement.service.ClubService;
import it.solvingteam.padelmanagement.service.CourtService;

@Component
public class CourtUpdateValidator implements Validator {

	@Autowired
	CourtService courtService;
	@Autowired
	ClubService clubService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return CourtDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		CourtDto courtDto = (CourtDto) target;
		Court courtDaDb = courtService.findById(Long.parseLong(courtDto.getId()));
		Club club = clubService.findById(courtDaDb.getClub().getId());
		for(Court court : club.getCourts()) {
			if(court.getName().equals(courtDto.getName())){
				errors.rejectValue("name", "nameAlreadyExists", "Esiste già un campo " + court.getName() + " !");
			}
		}
		for(Game game : courtDaDb.getGames()) {
			if(game.getDate().isAfter(LocalDate.now().minusDays(1)) 
						&& LocalTime.of(game.getSlots().iterator().next().getHour(), game.getSlots().iterator().next().getMinute()).isBefore(
								LocalTime.now().plusMinutes(30))) {
				errors.rejectValue("gamesDto", "gamesOpenExists", "Non puoi modificare un Campo attualmente prenotato!");
				break;
			}
		}
	}

}
