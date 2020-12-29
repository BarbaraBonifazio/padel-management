package it.solvingteam.padelmanagement.model.club;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import it.solvingteam.padelmanagement.model.admin.Admin;
import it.solvingteam.padelmanagement.model.court.Court;
import it.solvingteam.padelmanagement.model.joinProposal.JoinProposal;
import it.solvingteam.padelmanagement.model.notice.Notice;
import it.solvingteam.padelmanagement.model.player.Player;

@Entity
public class Club {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String city;
	private String address;
	
	@Lob @Basic(fetch = FetchType.EAGER)
	@Column(name = "LOGO")
	private Byte[] logo;
	
	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "admin_id", referencedColumnName = "id")
	private Admin admin;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "club", orphanRemoval = true)
	private List<JoinProposal> joinProposals = new ArrayList<>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "club", orphanRemoval = true)
	private List<Notice> notices = new ArrayList<>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "club", orphanRemoval = true)
	private List<Court> courts = new ArrayList<>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "club", orphanRemoval = true)
	private List<Player> players = new ArrayList<>();

	public Club() {
	}
	
	public Club(String name, String city, String address, Admin admin) {
		super();
		this.name = name;
		this.city = city;
		this.address = address;
		this.admin = admin;
	}

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
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Byte[] getLogo() {
		return logo;
	}

	public void setLogo(Byte[] logo) {
		this.logo = logo;
	}
	
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public List<JoinProposal> getJoinProposals() {
		return joinProposals;
	}

	public void setJoinProposals(List<JoinProposal> joinProposals) {
		this.joinProposals = joinProposals;
	}

	public List<Notice> getNotices() {
		return notices;
	}

	public void setNotices(List<Notice> notices) {
		this.notices = notices;
	}

	public List<Court> getCourts() {
		return courts;
	}

	public void setCourts(List<Court> courts) {
		this.courts = courts;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	
}
