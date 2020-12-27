package it.solvingteam.padelmanagement.model.admin;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import it.solvingteam.padelmanagement.model.club.Club;
import it.solvingteam.padelmanagement.model.user.User;

@Entity
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "club_id", referencedColumnName = "id")
	private Club club;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

}
