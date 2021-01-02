package it.solvingteam.padelmanagement.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.solvingteam.padelmanagement.dto.CourtDto;
import it.solvingteam.padelmanagement.dto.message.game.GameCheckDto;
import it.solvingteam.padelmanagement.mapper.court.CourtMapper;
import it.solvingteam.padelmanagement.model.court.Court;
import it.solvingteam.padelmanagement.model.game.Game;
import it.solvingteam.padelmanagement.model.player.Player;
import it.solvingteam.padelmanagement.model.slot.Slot;
import it.solvingteam.padelmanagement.repository.GameRepository;

@Service
public class GameService {

	@Autowired
	GameRepository gameRepository;
	@Autowired
	PlayerService playerService;
	@Autowired
	ClubService clubService;
	@Autowired
	SlotService slotService;
	@Autowired
	CourtMapper courtMapper;
	@Autowired
	CourtService courtService;

	public List<GameCheckDto> check(GameCheckDto gameCheckDto) {
		Player gameCreator = playerService.findPlayerWithClubEager(gameCheckDto.getPlayerId());
		List<Game> gamesBooked = gameRepository.listAllGamesBooked(LocalDate.parse(gameCheckDto.getDate()), gameCreator.getClub().getId());
		Set<Court> courtsNotAvailable = new HashSet<>();
		Set <CourtDto> allCourtsAvailable = new HashSet<>();
		Set<Court> allCourtsDaDbList = courtService.findAllCourtsByClub(gameCreator.getClub().getId()).stream().collect(Collectors.toSet());
		
		if(gamesBooked.isEmpty()) {
			for(Court courtDaDb : allCourtsDaDbList) {
				if(courtDaDb.getIsInactive() == false) {
					allCourtsAvailable.add(courtMapper.convertEntityToDto(courtDaDb)); 
				}
			}
		}
		
		Boolean trovato = false;
		int i=0;
		int j=0;
			for (Game game : gamesBooked){
			Court courtGame = game.getCourt();
				for(String slotId : gameCheckDto.getSlotsIds()) {
					Slot slotOccupied = slotService.findById(Long.parseLong(slotId));
					List <Slot> slotsDaDb = new ArrayList<>();
						slotsDaDb.add(slotOccupied);
						
						while (i < slotsDaDb.size()) {
							Slot slotCheck = (Slot) slotsDaDb.get(i);
							 
							  while(j < gamesBooked.size()) {
								 
								Game gameBooked = (Game) gamesBooked.get(j);
								List<Slot> slotsGameBooked = gameBooked.getSlots();
								
								for(Slot slotGame : slotsGameBooked) {
								trovato = slotGame.equals(slotCheck);
								
									if(trovato) {
										courtsNotAvailable.add(courtGame);
										j++;
										continue;
										
									}
								}
							  }
						}
							
				} //chiusura for gameCheckDto.getSlotsIds()
							
								for(Court courtNotAvailable : courtsNotAvailable){
									for(Court courtDaDb : allCourtsDaDbList) {
										if(!courtNotAvailable.equals(courtDaDb) && courtDaDb.getIsInactive() == false){
											allCourtsAvailable.add(courtMapper.convertEntityToDto(courtDaDb)); 
										}
									}
								}
				
				} //chiusura for per i GamedBooked
//						List<CourtDto> courtsList = allCourtsAvailable.stream().collect(Collectors.toList());
//								return courtsList;
					
			List<GameCheckDto> gamesAvailable = new ArrayList<>();
			for(CourtDto courtDto : allCourtsAvailable) {
//				GameCheckDto newGameDto  = new GameCheckDto();
//				newGameDto.setDate(gameCheckDto.getDate());
//				newGameDto.setPlayerId(gameCheckDto.getPlayerId());
//				newGameDto.setSlotsIds(gameCheckDto.getSlotsIds());
				gameCheckDto.setCourtDto(courtDto);
				gamesAvailable.add(gameCheckDto);
			}
			
			return gamesAvailable;
	}

}
