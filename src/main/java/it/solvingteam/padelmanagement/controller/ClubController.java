package it.solvingteam.padelmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.solvingteam.padelmanagement.dto.ClubDto;
import it.solvingteam.padelmanagement.service.ClubService;

@RestController
@RequestMapping("/api/club")
public class ClubController {

	@Autowired 
	ClubService clubService;
	
	@GetMapping("/")
	public ResponseEntity<List<ClubDto>> findAll(){
		List<ClubDto> newClubDto = clubService.findAll();
		 return ResponseEntity.status(HttpStatus.OK).body(newClubDto);
	}
	
}
