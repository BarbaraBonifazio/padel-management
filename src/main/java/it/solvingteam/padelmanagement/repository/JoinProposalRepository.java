package it.solvingteam.padelmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.solvingteam.padelmanagement.model.joinProposal.JoinProposal;

public interface JoinProposalRepository extends JpaRepository<JoinProposal, Long>{

	List<JoinProposal> findProposalByAspiringAssociate_Id(Long id);
	
	@Query("From JoinProposal j JOIN FETCH j.club c where c.admin.id = ?1")
	List<JoinProposal> findAllByAdminId(Long id);

}
