package it.solvingteam.padelmanagement.model.joinProposal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import it.solvingteam.padelmanagement.model.ProposalStatus;
import it.solvingteam.padelmanagement.model.club.Club;
import it.solvingteam.padelmanagement.model.user.User;

@Entity
public class JoinProposal {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Integer userLevel;
	
	@Enumerated(EnumType.STRING)
	private ProposalStatus proposalStatus;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User userCreator;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "club_id", nullable = false)
	private Club club;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}

	public ProposalStatus getProposalStatus() {
		return proposalStatus;
	}

	public void setProposalStatus(ProposalStatus proposalStatus) {
		this.proposalStatus = proposalStatus;
	}

	public User getUserCreator() {
		return userCreator;
	}

	public void setUserCreator(User userCreator) {
		this.userCreator = userCreator;
	}

	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}
	
}
