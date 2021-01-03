package it.solvingteam.padelmanagement.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.solvingteam.padelmanagement.dto.GameDto;
import it.solvingteam.padelmanagement.dto.message.game.GameCheckDto;
import it.solvingteam.padelmanagement.exception.BindingResultException;
import it.solvingteam.padelmanagement.service.GameService;
import it.solvingteam.padelmanagement.validator.GameCheckValidator;

@RestController
@RequestMapping("api/game")
public class GameController {

	@Autowired
	private GameCheckValidator gameCheckValidator;
	@Autowired
	private GameService gameService;
	
	//metodo verificaDisponibilità gameCheck
	
	@PostMapping("/availabilityCheck")
	public ResponseEntity<List<GameCheckDto>> gamesAvailability(@Valid @RequestBody GameCheckDto gameCheckDto, 
			BindingResult bindingResult) throws Exception {
		
		gameCheckValidator.validate(gameCheckDto, bindingResult);
		if(bindingResult.hasErrors()) {
			throw new BindingResultException(bindingResult);
		}
		
		List<GameCheckDto> gamesCheckDto = gameService.check(gameCheckDto);
		return ResponseEntity.status(HttpStatus.OK).body(gamesCheckDto);
	}
	
	@PostMapping("/insert")
	public ResponseEntity<GameDto> insert(@Valid @RequestBody GameCheckDto gameCheckDto, 
			BindingResult bindingResult) throws Exception {
		
		GameDto gameDto = gameService.insert(gameCheckDto);
		return ResponseEntity.status(HttpStatus.OK).body(gameDto);
	}
	
}
