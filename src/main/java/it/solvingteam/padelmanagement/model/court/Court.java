package it.solvingteam.padelmanagement.model.court;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import it.solvingteam.padelmanagement.model.club.Club;
import it.solvingteam.padelmanagement.model.game.Game;

@Entity
public class Court {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long courtId;
	
	private String name;
	private Boolean isInactive;
	private Integer bookingFee;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "club_id", nullable = false)
	private Club club;
	
	@OneToMany(mappedBy = "court")
	private List<Game> games;

	public Long getCourtId() {
		return courtId;
	}

	public void setCourtId(Long courtId) {
		this.courtId = courtId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsInactive() {
		return isInactive;
	}

	public void setIsInactive(Boolean isInactive) {
		this.isInactive = isInactive;
	}

	public Integer getBookingFee() {
		return bookingFee;
	}

	public void setBookingFee(Integer bookingFee) {
		this.bookingFee = bookingFee;
	}

	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	
}