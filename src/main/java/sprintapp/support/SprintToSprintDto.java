package sprintapp.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import sprintapp.model.Sprint;
import sprintapp.web.dto.SprintDTO;

@Component
public class SprintToSprintDto implements Converter<Sprint, SprintDTO>{

	@Override
	public SprintDTO convert(Sprint source) {
		SprintDTO dto = new SprintDTO();
		dto.setId(source.getId());
		dto.setSprintName(source.getSprintName());
		dto.setSprintPoints(source.getSprintPoints());
		return dto;
		
	}
	
	public List<SprintDTO> convert(List<Sprint> source) {
		List<SprintDTO> retVal = new ArrayList<>();
		
		for(Sprint s : source) {
			SprintDTO dto = convert(s);
			retVal.add(dto);
		}
		
		return retVal;
	}
	
	

}
