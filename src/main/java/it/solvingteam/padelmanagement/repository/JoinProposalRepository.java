package it.solvingteam.padelmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.solvingteam.padelmanagement.model.joinProposal.JoinProposal;

public interface JoinProposalRepository extends JpaRepository<JoinProposal, Long>{

	List<JoinProposal> findProposalByAspiringAssociate_Id(Long id);

}
