package it.solvingteam.padelmanagement;

import java.time.LocalDate;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.solvingteam.padelmanagement.dto.NoticeDto;
import it.solvingteam.padelmanagement.dto.message.SuccessMessageDto;
import it.solvingteam.padelmanagement.dto.message.notice.InsertNoticeDto;
import it.solvingteam.padelmanagement.mapper.notice.NoticeMapper;
import it.solvingteam.padelmanagement.model.club.Club;
import it.solvingteam.padelmanagement.model.notice.Notice;
import it.solvingteam.padelmanagement.model.player.Player;
import it.solvingteam.padelmanagement.repository.NoticeRepository;
import it.solvingteam.padelmanagement.service.ClubService;
import it.solvingteam.padelmanagement.service.PlayerService;

@Service
public class NoticeService {
	
	@Autowired
	NoticeRepository noticeRepository;
	@Autowired
	ClubService clubService;
	@Autowired
	NoticeMapper noticeMapper;
	@Autowired
	PlayerService playerService;
	
	public NoticeDto insert(InsertNoticeDto insertNoticeDto) {
		Club club = clubService.findClubByAdmin(Long.parseLong(insertNoticeDto.getAdminId()));
		Notice entity = new Notice(insertNoticeDto.getMessage(), LocalDate.now(), club);
		noticeRepository.save(entity);
		return noticeMapper.convertEntityToDto(entity);
	}
	
	public Notice findById(String id) {
		return this.noticeRepository.findById(Long.parseLong(id)).get();
	}
	
	public NoticeDto findNoticeDtoById(String id) throws Exception {
		if(!StringUtils.isNumeric(id)) {
			throw new Exception("L'id fornito non esiste!");
		}
		 Notice entity = this.noticeRepository.findById(Long.parseLong(id)).get();
		 if(entity==null) {
	            throw new Exception("L'id fornito non è valido!");
	        }
		 return noticeMapper.convertEntityToDto(entity);
	}

	public List<NoticeDto> findAllNoticesForAdmin(String adminId) {
			Club club = clubService.findClubByAdmin(Long.parseLong(adminId));
			List<Notice> notices = noticeRepository.findAllNoticeByClub_Id(club.getId());
			return noticeMapper.convertEntityToDto(notices);
		}

	public NoticeDto update(NoticeDto noticeDto) {
		Notice noticeDaDb = this.findById(noticeDto.getId());
		noticeDaDb.setMessage(noticeDto.getMessage());
		noticeRepository.save(noticeDaDb);
		return noticeMapper.convertEntityToDto(noticeDaDb);
	}

	public SuccessMessageDto delete(String noticeId) throws Exception {
		 if(!StringUtils.isNumeric(noticeId)) {
	            throw new Exception("L'id fornito non esiste!");
	        }
		Notice entity = noticeRepository.findById(Long.parseLong(noticeId)).get();
		if(entity==null) {
            throw new Exception("L'id fornito non è valido!");
        }
		noticeRepository.delete(entity);
		return new SuccessMessageDto("La notizia selezionata è stata eliminata correttamente!");
	}

	public List<NoticeDto> findAllNoticesForPlayers(String playerId) {
		Player player = playerService.findPlayerWithClubEager(playerId);
		List<Notice> notices = noticeRepository.findAllNoticeByClub_Id(player.getClub().getId());
		return noticeMapper.convertEntityToDto(notices);
	}
	
}
