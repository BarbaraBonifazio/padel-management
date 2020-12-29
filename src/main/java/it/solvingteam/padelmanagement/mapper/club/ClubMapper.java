package it.solvingteam.padelmanagement.mapper.club;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.solvingteam.padelmanagement.dto.ClubDto;
import it.solvingteam.padelmanagement.mapper.AbstractMapper;
import it.solvingteam.padelmanagement.mapper.court.CourtMapper;
import it.solvingteam.padelmanagement.mapper.joinProposal.JoinProposalMapper;
import it.solvingteam.padelmanagement.mapper.notice.NoticeMapper;
import it.solvingteam.padelmanagement.mapper.player.PlayerMapper;
import it.solvingteam.padelmanagement.model.club.Club;

@Component
public class ClubMapper extends AbstractMapper<Club, ClubDto> {

	@Autowired
	JoinProposalMapper joinProposalMapper;
	@Autowired
	NoticeMapper noticeMapper;
	@Autowired
	CourtMapper courtMapper;
	@Autowired
	PlayerMapper playerMapper;

	@Override
	public ClubDto convertEntityToDto(Club entity) {
		if (entity == null) {
			return null;
		}

		ClubDto dto = new ClubDto();
		dto.setId(String.valueOf(entity.getId()));
		dto.setName(entity.getName());
		dto.setCity(entity.getCity());
		dto.setLogo(entity.getLogo());
		dto.setAddress(entity.getAddress());
		dto.setJoinProposalsDto(joinProposalMapper.convertEntityToDto(entity.getJoinProposals()));
		dto.setNoticesDto(noticeMapper.convertEntityToDto(entity.getNotices()));
		dto.setCourtsDto(courtMapper.convertEntityToDto(entity.getCourts()));
		dto.setPlayersDto(playerMapper.convertEntityToDto(entity.getPlayers()));

		return dto;

	}

	@Override
	public Club convertDtoToEntity(ClubDto dto) {
		if (dto == null) {
			return null;
		}

		Club club = new Club();
		
		if(dto.getId() != null) {
			club.setId(Long.parseLong(dto.getId()));
		}
		
		club.setName(dto.getName());
		club.setCity(dto.getCity());
		club.setLogo(dto.getLogo());
		club.setAddress(dto.getAddress());
		club.setJoinProposals(joinProposalMapper.convertDtoToEntity(dto.getJoinProposalsDto()));
		club.setNotices(noticeMapper.convertDtoToEntity(dto.getNoticesDto()));
		club.setCourts(courtMapper.convertDtoToEntity(dto.getCourtsDto()));
		club.setPlayers(playerMapper.convertDtoToEntity(dto.getPlayersDto()));

		return club;
	}

}
