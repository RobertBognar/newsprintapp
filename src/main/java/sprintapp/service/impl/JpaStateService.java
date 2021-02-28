package sprintapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sprintapp.model.State;
import sprintapp.repository.StateRepository;
import sprintapp.service.StateService;

@Service
public class JpaStateService implements StateService {
	
	@Autowired
	private StateRepository stateRepository;

	@Override
	public List<State> findAll() {
		return stateRepository.findAll();
	}

	@Override
	public Optional<State> findOne(Long id) {
		return stateRepository.findById(id);
	}

	@Override
	public State save(State state) {
		return stateRepository.save(state);
	}
	
	

}
