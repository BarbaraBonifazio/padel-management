package it.solvingteam.padelmanagement.validator;

import java.time.LocalDate;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.solvingteam.padelmanagement.dto.message.game.GameCheckDto;

@Component
public class GameCheckValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return GameCheckDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		GameCheckDto gameCheckDto = (GameCheckDto) target;

			try {
				if (LocalDate.parse(gameCheckDto.getDate()).isBefore(LocalDate.now())) {
					errors.rejectValue("date", "dateError", "Data non valida");
				}
			} catch (Exception e) {
				errors.rejectValue("date", "dateError", "Data inserita non valida");
			  }	
			
			if(gameCheckDto.getSlotsIds().size() < 3) {
				errors.rejectValue("slotsIds", "bookingSlotInsufficient", "Devi prenotare almeno un'ora e mezza di partita!");
			}
	}

}
