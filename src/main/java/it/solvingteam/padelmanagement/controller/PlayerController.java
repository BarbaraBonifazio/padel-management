package it.solvingteam.padelmanagement.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.solvingteam.padelmanagement.NoticeService;
import it.solvingteam.padelmanagement.dto.GameDto;
import it.solvingteam.padelmanagement.dto.NoticeDto;
import it.solvingteam.padelmanagement.dto.message.SuccessMessageDto;
import it.solvingteam.padelmanagement.dto.message.game.GameCheckDto;
import it.solvingteam.padelmanagement.dto.message.game.GameUpdateDto;
import it.solvingteam.padelmanagement.dto.message.game.GameUpdateMissingPlayersDto;
import it.solvingteam.padelmanagement.exception.BindingResultException;
import it.solvingteam.padelmanagement.model.player.Player;
import it.solvingteam.padelmanagement.service.GameService;
import it.solvingteam.padelmanagement.service.PlayerService;
import it.solvingteam.padelmanagement.util.TokenDecripter;
import it.solvingteam.padelmanagement.validator.GameCheckValidator;

@RestController
@RequestMapping("/api/player")
public class PlayerController {
	
	@Autowired
	private PlayerService playerService;
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private GameCheckValidator gameCheckValidator;
	@Autowired
	private GameService gameService;
	
	
	//ENDPOINTS GAMES (Player Creatore di Partite)
	
	@PostMapping("/availabilityGameCheck")
	public ResponseEntity<List<GameCheckDto>> gamesAvailability(@Valid @RequestBody GameCheckDto gameCheckDto, 
			BindingResult bindingResult) throws Exception {
		String username = TokenDecripter.decripter();
		Player player = playerService.findByUsername(username); 
		gameCheckDto.setPlayerId(String.valueOf(player.getId()));
		gameCheckValidator.validate(gameCheckDto, bindingResult);
		if(bindingResult.hasErrors()) {
			throw new BindingResultException(bindingResult);
		}
		
		List<GameCheckDto> gamesCheckDto = gameService.check(gameCheckDto);
		return ResponseEntity.status(HttpStatus.OK).body(gamesCheckDto);
	}
	
	@PostMapping("/insertNewGame")
	public ResponseEntity<GameDto> insert(@Valid @RequestBody GameCheckDto gameCheckDto, 
			BindingResult bindingResult) throws Exception {
		String username = TokenDecripter.decripter();
		Player player = playerService.findByUsername(username); 
		gameCheckDto.setPlayerId(String.valueOf(player.getId()));
		GameDto gameDto = gameService.insert(gameCheckDto);
		return ResponseEntity.status(HttpStatus.OK).body(gameDto);
	}

	@GetMapping("/findAllCreatedGames")
	public ResponseEntity<List<GameDto>> findAll(){
		String username = TokenDecripter.decripter();
		Player player = playerService.findByUsername(username); 
		List<GameDto> gameDto = gameService.findAll(player.getId());
		 return ResponseEntity.status(HttpStatus.OK).body(gameDto);
	}
	
	@PutMapping("/updateCreatedGame")
	public ResponseEntity <List<GameCheckDto>> update(@Valid @RequestBody GameUpdateDto gameUpdateDto, 
			BindingResult bindingResult) throws Exception {

		if(bindingResult.hasErrors()) {
			throw new BindingResultException(bindingResult);
		}		
		List<GameCheckDto> gameCheckDto = gameService.update(gameUpdateDto);
		return ResponseEntity.status(HttpStatus.OK).body(gameCheckDto);
	}
	
	
	@DeleteMapping("/deleteCreatedGame/{gameId}")
	public ResponseEntity<SuccessMessageDto> delete(@PathVariable String gameId) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(gameService.delete(gameId));
	}
	
	@PutMapping("/updateMissingPlayersForGamesCreated")
	public ResponseEntity <GameDto> updateMissingPlayers(@Valid @RequestBody GameUpdateMissingPlayersDto gameUpdateMissingPlayersDto, 
			BindingResult bindingResult) throws Exception {

		if(bindingResult.hasErrors()) {
			throw new BindingResultException(bindingResult);
		}		
		GameDto gameDto = gameService.updateMissingPlayers(gameUpdateMissingPlayersDto);
		return ResponseEntity.status(HttpStatus.OK).body(gameDto);
	}
	
//<--FINE ENDPOINTS GAMES -->
	
	
	//ENDPOINTS CallForAction (Player Giocatore)
	
	@GetMapping("showCallForActionList")
	public ResponseEntity<List<GameDto>> showCallForActions() throws Exception {
		String username = TokenDecripter.decripter();
		Player player = playerService.findByUsername(username); 
		return ResponseEntity.status(HttpStatus.OK).body(gameService.findOpenMatches(player.getId()));
	}
	
	@GetMapping("/joinCallForAction/{gameId}")
	public ResponseEntity<SuccessMessageDto> joinCallForAction(@PathVariable String gameId) throws Exception {
		String username = TokenDecripter.decripter();
		Player player = playerService.findByUsername(username); 
		return ResponseEntity.status(HttpStatus.OK).body(gameService.joinCallForAction(player, gameId));
	}
	
//<--FINE ENDPOINTS CallForAction -->	
	
	
	//ENDPOINT NOTICES DASHBOARD
	
	@GetMapping("/listAllNotices")
	public ResponseEntity<List<NoticeDto>> dashboardPlayerListAllNotices(){
		String username = TokenDecripter.decripter();
		Player player = playerService.findByUsername(username);
		List<NoticeDto> noticeDto = noticeService.findAllNoticesForPlayers(player.getId());
		 return ResponseEntity.status(HttpStatus.OK).body(noticeDto);
	}
	
//<--FINE ENDPOINTS NOTICES DASHBOARD -->
	
	
}
