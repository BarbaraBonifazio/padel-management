package it.solvingteam.padelmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.solvingteam.padelmanagement.model.slot.Slot;
import it.solvingteam.padelmanagement.repository.SlotRepository;

@Service
public class SlotService {

	@Autowired
	SlotRepository slotRepository;

	public Slot findById(Long slotId) {
		return slotRepository.findById(slotId).get();
	}
	
	
	
}
