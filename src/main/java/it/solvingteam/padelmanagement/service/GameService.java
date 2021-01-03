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
import it.solvingteam.padelmanagement.dto.GameDto;
import it.solvingteam.padelmanagement.dto.message.game.GameCheckDto;
import it.solvingteam.padelmanagement.mapper.court.CourtMapper;
import it.solvingteam.padelmanagement.mapper.game.GameMapper;
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
	@Autowired
	GameMapper gameMapper;
	@Autowired
	EmailService emailService;

	public List<GameCheckDto> check(GameCheckDto gameCheckDto) throws Exception {
		Player gameCreator = playerService.findPlayerWithClubEager(gameCheckDto.getPlayerId());
		List<Game> gamesBooked = gameRepository.listAllGamesBooked(LocalDate.parse(gameCheckDto.getDate()),
				gameCreator.getClub().getId());
		Set<CourtDto> allCourtsNotAvailable = new HashSet<>();
		Set<CourtDto> allCourtsAvailable = new HashSet<>();
		Set<CourtDto> allCourtsDaDbList = courtService.findAllCourtsByClub(gameCreator.getClub().getId()).stream().collect(Collectors.toSet());
		
		//se non ci sono partite prenotate per data e fascia oraria (slot) in input, ritorna direttamente tutte le partite prenotabili saltando gli altri controlli
		Set<GameCheckDto> gamesBookableList = new HashSet<>();
		
		if (gamesBooked.isEmpty()) {
			for (CourtDto courtDtoDaDb : allCourtsDaDbList) {
				if (courtDtoDaDb.getIsInactive() == false) {
					allCourtsAvailable.add(courtDtoDaDb);
					
				}
			}	
					for(CourtDto availableCourtDto : allCourtsAvailable) {
						
							GameCheckDto newGameCheckDto = new GameCheckDto();
							
							newGameCheckDto.setDate(gameCheckDto.getDate());
							newGameCheckDto.setMissingPlayers(gameCheckDto.getMissingPlayers());
							newGameCheckDto.setPlayerId(gameCheckDto.getPlayerId());
							newGameCheckDto.setSlotsIds(gameCheckDto.getSlotsIds());
							newGameCheckDto.setCourtDto(availableCourtDto);

							gamesBookableList.add(newGameCheckDto);
	
					}
			return gamesBookableList.stream().collect(Collectors.toList());
		}
		// ---- Fine ritorno di tutte le partite prenotabili ----
		
		
		//se ci sono partite prenotate, carica gli slot prenotati da DB
		List<Slot> slotsDaDBList = new ArrayList<>();
		for (String slotIdBookingProposal : gameCheckDto.getSlotsIds()) {
			Slot slotBookingPropposalDaDb = slotService.findById(Long.parseLong(slotIdBookingProposal));		
			slotsDaDBList.add(slotBookingPropposalDaDb);
		}
		
		//trova campi non disponibili in base agli slot passati in input
		Boolean gamesBookedFound = false;
		for (Game game : gamesBooked) {
			CourtDto court = courtService.findCourtByGames_Id(game.getId());	
			
				for (Slot slotDaDb : slotsDaDBList) {
					for (Slot slotBooked : game.getSlots()) {
						gamesBookedFound = slotBooked.equals(slotDaDb);
						
						if (gamesBookedFound) {
							allCourtsNotAvailable.add(court);
							
						} 
					}
				}
		}
		
		//se non trova slot corrispondenti a quelli passati in input, ritorna tutti i campi disponibili per quegli slot
		if(allCourtsNotAvailable.isEmpty()) {  
			for (CourtDto courtDtoDaDb : allCourtsDaDbList) {
				if (courtDtoDaDb.getIsInactive() == false) {
					allCourtsAvailable.add(courtDtoDaDb);
					
				}
			}	
			for(CourtDto availableCourtDto : allCourtsAvailable) {
				
				GameCheckDto newGameCheckDto = new GameCheckDto();
				
				newGameCheckDto.setDate(gameCheckDto.getDate());
				newGameCheckDto.setMissingPlayers(gameCheckDto.getMissingPlayers());
				newGameCheckDto.setPlayerId(gameCheckDto.getPlayerId());
				newGameCheckDto.setSlotsIds(gameCheckDto.getSlotsIds());
				newGameCheckDto.setCourtDto(availableCourtDto);

				gamesBookableList.add(newGameCheckDto);

			}
			return gamesBookableList.stream().collect(Collectors.toList());
		}
		
		//trova campi disponibili per la prenotazione confrontando tutti i campi da DB con quelli non disponibili poichè già prenotati 
		Boolean available = false;
		for(CourtDto courtDtoDaDb : allCourtsDaDbList) {
			
			for(CourtDto courtNotAvailableDto : allCourtsNotAvailable) {
				if(courtDtoDaDb.getId().equals(courtNotAvailableDto.getId())) {
					available = false;
					break;
				} else {
					available = true;
				}
			}
				if(available && courtDtoDaDb.getIsInactive() == false) {
					allCourtsAvailable.add(courtDtoDaDb);
				}
		}
		
		//ritorna la lista di partite prenotabili, impostando i campi prenotabili in base a data e slot		
		for(CourtDto availableCourtDto : allCourtsAvailable) {
			GameCheckDto newGameCheckDto = new GameCheckDto();
			newGameCheckDto.setCourtDto(availableCourtDto);
			newGameCheckDto.setDate(gameCheckDto.getDate());
			newGameCheckDto.setMissingPlayers(gameCheckDto.getMissingPlayers());
			newGameCheckDto.setPlayerId(gameCheckDto.getPlayerId());
			newGameCheckDto.setSlotsIds(gameCheckDto.getSlotsIds());
			
				gamesBookableList.add(newGameCheckDto);
			}
		
		return gamesBookableList.stream().collect(Collectors.toList());
	}


	public GameDto insert(GameCheckDto gameCheckDto) throws Exception {
		
		//operazione di insert tradesafe, controllo se la partita richiesta è ancora prenotabile
		boolean gameBookable = false;
		List<GameCheckDto> bookableGames = this.check(gameCheckDto); 
		for(GameCheckDto bookableGame : bookableGames) {
			if (gameCheckDto.getCourtDto().getId().equals(bookableGame.getCourtDto().getId())) {
				gameBookable = true;
				break;
			} else {
				gameBookable = false;
			}
		}
		
			//se la partita risulta ancora prenotabile, si procede
			if (gameBookable) {
				Player player = playerService.findById(Long.parseLong(gameCheckDto.getPlayerId()));
				List<Slot> slotBooked = new ArrayList<>();
				for (String slot : gameCheckDto.getSlotsIds()) {
					Slot slotDaDb = slotService.findById(Long.parseLong(slot));
					slotBooked.add(slotDaDb);
				} 
				
				Game game = new Game();
				game.setPaid(Boolean.FALSE);
				game.setMissingPlayers(Integer.parseInt(gameCheckDto.getMissingPlayers()));
				game.setDate(LocalDate.parse(gameCheckDto.getDate()));
				game.setCourt(courtMapper.convertDtoToEntity(gameCheckDto.getCourtDto()));
				game.setGameCreator(player);
				game.setSlots(slotBooked);
	
				Game gameDaDb = gameRepository.save(game);
				
				
				if(gameDaDb.getMissingPlayers() == 0) {
					emailService.sendMail(player.getUser().getMailAddress(), " Partita Prenotata ", 
							" Gentile Utente " + player.getUser().getName() + " " + player.getUser().getSurname() + ", " 
							+ "\n" + "\n" +
							"siamo lieti di comunicarle che la seguente partita risulta correttamente prenotata: " 
							+ "\n" + "\n" + 
							" " + gameDaDb.toString() + " "
							+ "\n" + 
							"Le auguriamo Buon Divertimento! " + 
							"\n" + "\n" +
							"Cordiali saluti, "
							+ "\n" +
							"- Team Padel Management");
				}
				return gameMapper.convertEntityToDto(gameDaDb);
				
			} else {  //altrimenti ritorna un'eccezione
				throw new Exception("Il campo scelto non è disponibile per l'orario richiesto!");
			}

	}

	public List<GameDto> findAll(String playerId) {
		List<Game> games = gameRepository.findAllGameByGameCreator_Id(Long.parseLong(playerId));
		return gameMapper.convertEntityToDto(games);
	}

}
