package it.solvingteam.padelmanagement.mapper.slot;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import it.solvingteam.padelmanagement.mapper.AbstractMapper;

@Component
public class SlotMapper extends AbstractMapper<Long, String>{

	@Override
	public String convertEntityToDto(Long entityId) {
		if(entityId == null) {
			return null;
		}
		
		String dtoId = String.valueOf(entityId);
		
		return dtoId;
		
	}

	@Override
	public Long convertDtoToEntity(String dtoId) {
		if(dtoId == null) {
			return null;
		}
		
		Long entityId = Long.parseLong(dtoId);
		
		return entityId;
	}
	
	public List<String> convertEntityToDto(List<Long> entitiesIds) {
        if (entitiesIds == null) {
            return null;
        }

        List<String> dtoIds = new ArrayList<>();

        for (Long entityId : entitiesIds) {
            dtoIds.add(convertEntityToDto(entityId));
        }

        return dtoIds;
    }

    public List<Long> convertDtoToEntity(List<String> dtoIds) {
        if (dtoIds == null) {
            return null;
        }

        List<Long> entitiesIds = new ArrayList<>();

        for (String dtoId : dtoIds) {
            entitiesIds.add(convertDtoToEntity(dtoId));
        }

        return entitiesIds;
    }

}
