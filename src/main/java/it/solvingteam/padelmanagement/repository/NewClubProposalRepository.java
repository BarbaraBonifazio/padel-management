package it.solvingteam.padelmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.solvingteam.padelmanagement.model.newClubProposal.NewClubProposal;

public interface NewClubProposalRepository extends JpaRepository<NewClubProposal, Long>{

	public List<NewClubProposal> findAllNewClubProposalByClubCreator_Id(Long id);

}
