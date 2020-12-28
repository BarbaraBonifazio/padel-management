package it.solvingteam.padelmanagement.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class ClubDto {

	@NotNull
	private String id;
	private String name;
	private String city;
	private Byte[] logo;
	private String address;
	
	@Valid
	private AdminDto adminDto;
	@Valid
	private List<JoinProposalDto> joinProposalsDto;
	@Valid
	private List<NoticeDto> noticesDto;
	@Valid
	private List<CourtDto> courtsDto;
	@Valid
	private List<PlayerDto> playersDto;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Byte[] getLogo() {
		return logo;
	}
	public void setLogo(Byte[] logo) {
		this.logo = logo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public AdminDto getAdminDto() {
		return adminDto;
	}
	public void setAdminDto(AdminDto adminDto) {
		this.adminDto = adminDto;
	}
	public List<JoinProposalDto> getJoinProposalsDto() {
		return joinProposalsDto;
	}
	public void setJoinProposalsDto(List<JoinProposalDto> joinProposalsDto) {
		this.joinProposalsDto = joinProposalsDto;
	}
	public List<NoticeDto> getNoticesDto() {
		return noticesDto;
	}
	public void setNoticesDto(List<NoticeDto> noticesDto) {
		this.noticesDto = noticesDto;
	}
	public List<CourtDto> getCourtsDto() {
		return courtsDto;
	}
	public void setCourtsDto(List<CourtDto> courtsDto) {
		this.courtsDto = courtsDto;
	}
	public List<PlayerDto> getPlayersDto() {
		return playersDto;
	}
	public void setPlayersDto(List<PlayerDto> playersDto) {
		this.playersDto = playersDto;
	}
	
}
