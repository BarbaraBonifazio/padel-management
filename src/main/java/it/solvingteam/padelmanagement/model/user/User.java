package it.solvingteam.padelmanagement.model.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import it.solvingteam.padelmanagement.model.joinProposal.JoinProposal;
import it.solvingteam.padelmanagement.model.newClubProposal.NewClubProposal;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String surname;
	private String mailAddress;
	private String mobile;
	private LocalDate dateOfBirth;
	private String username;
	private String password;

	@Lob
	@Basic(fetch = FetchType.EAGER)
	@Column(name = "PROFILE_PIC")
	private Byte[] profilePic;

	@Enumerated(EnumType.STRING)
	private Role role;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", orphanRemoval = true)
	private List<JoinProposal> joinProposals = new ArrayList<>();
	
	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "newClubProposal_id", referencedColumnName = "id")
	private NewClubProposal newClubProposal;

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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Byte[] getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(Byte[] profilePic) {
		this.profilePic = profilePic;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<JoinProposal> getJoinProposals() {
		return joinProposals;
	}

	public void setJoinProposals(List<JoinProposal> joinProposals) {
		this.joinProposals = joinProposals;
	}

	public NewClubProposal getNewClubProposal() {
		return newClubProposal;
	}

	public void setNewClubProposal(NewClubProposal newClubProposal) {
		this.newClubProposal = newClubProposal;
	}
	
}
