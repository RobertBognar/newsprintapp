package sprintapp.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import sprintapp.model.State;
import sprintapp.web.dto.StateDTO;

@Component
public class StateToStateDto implements Converter<State, StateDTO> {

	@Override
	public StateDTO convert(State source) {
		StateDTO dto = new StateDTO();
		dto.setId(source.getId());
		dto.setStateName(source.getStateName());
		return dto;
	}
	
	public List<StateDTO> convert(List<State> source) {
		List<StateDTO> retVal = new ArrayList<>();
		
		for(State s : source ) {
			StateDTO dto = convert(s);
			retVal.add(dto);
		}
		
		return retVal;
	}
	
	

}
