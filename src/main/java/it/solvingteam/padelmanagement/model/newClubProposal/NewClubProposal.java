package it.solvingteam.padelmanagement.model.newClubProposal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import it.solvingteam.padelmanagement.model.ProposalStatus;
import it.solvingteam.padelmanagement.model.user.User;

@Entity
public class NewClubProposal {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String city;

	@Enumerated(EnumType.STRING)
	private ProposalStatus proposalStatus;

	@Lob
	@Basic(fetch = FetchType.EAGER)
	@Column(name = "LOGO")
	private Byte[] logo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User userCreator;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public ProposalStatus getProposalStatus() {
		return proposalStatus;
	}

	public void setProposalStatus(ProposalStatus proposalStatus) {
		this.proposalStatus = proposalStatus;
	}

	public Byte[] getLogo() {
		return logo;
	}

	public void setLogo(Byte[] logo) {
		this.logo = logo;
	}

	public User getUserCreator() {
		return userCreator;
	}

	public void setUserCreator(User userCreator) {
		this.userCreator = userCreator;
	}

}
