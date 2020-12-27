package it.solvingteam.padelmanagement.model.player;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import it.solvingteam.padelmanagement.model.club.Club;
import it.solvingteam.padelmanagement.model.game.Game;
import it.solvingteam.padelmanagement.model.user.User;

@Entity
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Integer playerLevel;
	
	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "club_id", nullable = false)
	private Club club;
	
	@OneToMany(mappedBy = "player")
	private List<Game> games = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPlayerLevel() {
		return playerLevel;
	}

	public void setPlayerLevel(Integer playerLevel) {
		this.playerLevel = playerLevel;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
