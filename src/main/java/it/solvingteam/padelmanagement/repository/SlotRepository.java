package it.solvingteam.padelmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.solvingteam.padelmanagement.model.slot.Slot;

public interface SlotRepository extends JpaRepository<Slot, Long>{

}
