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

import it.solvingteam.padelmanagement.dto.CourtDto;
import it.solvingteam.padelmanagement.dto.JoinProposalDto;
import it.solvingteam.padelmanagement.dto.NoticeDto;
import it.solvingteam.padelmanagement.dto.message.SuccessMessageDto;
import it.solvingteam.padelmanagement.dto.message.court.InsertCourtDto;
import it.solvingteam.padelmanagement.dto.message.notice.InsertNoticeDto;
import it.solvingteam.padelmanagement.exception.BindingResultException;
import it.solvingteam.padelmanagement.model.admin.Admin;
import it.solvingteam.padelmanagement.service.AdminService;
import it.solvingteam.padelmanagement.service.CourtService;
import it.solvingteam.padelmanagement.service.JoinProposalService;
import it.solvingteam.padelmanagement.service.NoticeService;
import it.solvingteam.padelmanagement.util.TokenDecripter;
import it.solvingteam.padelmanagement.validator.CourtUpdateValidator;
import it.solvingteam.padelmanagement.validator.CourtValidator;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	JoinProposalService joinProposalService;
	@Autowired
	AdminService adminService;
	@Autowired
	CourtService courtService;
	@Autowired
	CourtValidator courtValidator;
	@Autowired
	CourtUpdateValidator courtUpdateValidator;
	@Autowired
	NoticeService noticeService;
	
	//ENDPOINTS JOINPROPOSALS
	
	@GetMapping("/approveJP/{idJoinProposal}")
	public ResponseEntity<SuccessMessageDto> approved(@PathVariable String idJoinProposal) throws Exception {
		joinProposalService.joinProposalApproval(idJoinProposal);
		return ResponseEntity.status(HttpStatus.OK).body(new SuccessMessageDto("La proposta di adesione è stata correttamente approvata"));
	}
	
	@GetMapping("/rejectJP/{idJoinProposal}")
	public ResponseEntity<SuccessMessageDto> rejected(@PathVariable String idJoinProposal) throws Exception {
		joinProposalService.joinProposalRejection(idJoinProposal);
		return ResponseEntity.status(HttpStatus.OK).body(new SuccessMessageDto("La proposta di adesione è stata rifiutata"));
	}
	
	@GetMapping("/findAllJP")
	public ResponseEntity<List<JoinProposalDto>> findAllJoinProposals(){
		String username = TokenDecripter.decripter();
		Admin admin = adminService.findByUsername(username);
		List<JoinProposalDto> joinProposalsDto = joinProposalService.findAllByClub(admin.getId());
		 return ResponseEntity.status(HttpStatus.OK).body(joinProposalsDto);
	}
	
//<--FINE ENDPOINTS JOINPROPOSALS -->
	
	//ENDPOINTS COURTS
	
	@PostMapping("/insertCourt")
	public ResponseEntity<CourtDto> insertCourt(@Valid @RequestBody InsertCourtDto insertCourtDto, 
			BindingResult bindingResult) throws Exception {
		String username = TokenDecripter.decripter();
		Admin admin = adminService.findByUsername(username); 
		insertCourtDto.setAdminId(String.valueOf(admin.getId()));
		courtValidator.validate(insertCourtDto, bindingResult);
		if(bindingResult.hasErrors()) {
			throw new BindingResultException(bindingResult);
		}
		CourtDto courtDto = courtService.insert(insertCourtDto);
		return ResponseEntity.status(HttpStatus.OK).body(courtDto);
	}
	
	@GetMapping("/listAllCourts")
	public ResponseEntity<List<CourtDto>> findAllCourts(){
		String username = TokenDecripter.decripter();
		Admin admin = adminService.findByUsername(username);
		List<CourtDto> courtDto = courtService.findAll(admin.getId());
		 return ResponseEntity.status(HttpStatus.OK).body(courtDto);
	}
	
	@GetMapping("/showCourt/{courtId}")
	public ResponseEntity<CourtDto> show(@PathVariable String courtId) throws Exception {
		CourtDto courtDto = courtService.findCourtDtoById(courtId);
		 return ResponseEntity.status(HttpStatus.OK).body(courtDto);
	}
	
	@PutMapping("/updateCourt")
	public ResponseEntity<CourtDto> update(@Valid @RequestBody CourtDto courtDto, 
			BindingResult bindingResult) throws Exception {
		
		courtUpdateValidator.validate(courtDto, bindingResult);
		if(bindingResult.hasErrors()) {
			throw new BindingResultException(bindingResult);
		}
		courtDto = courtService.update(courtDto);
		return ResponseEntity.status(HttpStatus.OK).body(courtDto);
	}
	
	@DeleteMapping("/deleteCourt/{courtId}")
	public ResponseEntity<SuccessMessageDto> delete(@PathVariable String courtId) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(courtService.setStatus(courtId));
	}
	
//<--FINE ENDPOINTS COURTS -->	
	
	
	//ENDPOINTS NOTICES DASHBOARD
	
	@PostMapping("/insertNotice")
	public ResponseEntity<NoticeDto> insert(@Valid @RequestBody InsertNoticeDto insertNoticeDto, 
					BindingResult bindingResult) throws Exception {
		String username = TokenDecripter.decripter();
		Admin admin = adminService.findByUsername(username); 
		insertNoticeDto.setAdminId(String.valueOf(admin.getId()));
		if(bindingResult.hasErrors()) {
			throw new BindingResultException(bindingResult);
		}
		NoticeDto noticeDto = noticeService.insert(insertNoticeDto);
		return ResponseEntity.status(HttpStatus.OK).body(noticeDto);
	}
	
	@GetMapping("/listAllNotices")
	public ResponseEntity<List<NoticeDto>> dashboardAdminListAllNotices(){
		String username = TokenDecripter.decripter();
		Admin admin = adminService.findByUsername(username);
		List<NoticeDto> noticeDto = noticeService.findAllNoticesForAdmin(admin.getId());
		 return ResponseEntity.status(HttpStatus.OK).body(noticeDto);
	}
	
	@GetMapping("/showNotice/{noticeId}")
	public ResponseEntity<NoticeDto> dashboardAdminShowNotice(@PathVariable String noticeId) throws Exception {
		NoticeDto noticeDto = noticeService.findNoticeDtoById(noticeId);
		 return ResponseEntity.status(HttpStatus.OK).body(noticeDto);
	}
	
	@PutMapping("/updateNotice")
	public ResponseEntity<NoticeDto> dashboardAdminUpdateNotice(@Valid @RequestBody NoticeDto noticeDto, 
			BindingResult bindingResult) throws Exception {
		
		if(bindingResult.hasErrors()) {
			throw new BindingResultException(bindingResult);
		}
		noticeDto = noticeService.update(noticeDto);
		return ResponseEntity.status(HttpStatus.OK).body(noticeDto);
	}
	
	@DeleteMapping("/deleteNotice/{noticeId}")
	public ResponseEntity<SuccessMessageDto> dashboardAdminDeleteNotice(@PathVariable String noticeId) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(noticeService.delete(noticeId));
	}
	
//<--FINE ENDPOINTS NOTICES DASHBOARD -->
	
	
	
}
