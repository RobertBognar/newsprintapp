package sprintapp.service;

import java.util.List;
import java.util.Optional;

import sprintapp.model.State;

public interface StateService {
	
	List<State> findAll();
	Optional<State> findOne(Long id);
	State save(State state);

}
